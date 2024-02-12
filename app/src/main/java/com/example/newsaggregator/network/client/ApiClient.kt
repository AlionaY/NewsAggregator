package com.example.newsaggregator.network.client

import com.example.newsaggregator.network.AppException
import com.example.newsaggregator.network.BaseUrlHolder
import com.example.newsaggregator.network.ServerException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import io.ktor.serialization.kotlinx.xml.xml
import kotlinx.serialization.json.Json
import nl.adaptivity.xmlutil.XmlDeclMode
import nl.adaptivity.xmlutil.serialization.XML

class ApiClient(private val baseUrlHolder: BaseUrlHolder) {

    companion object {
        const val CONNECT_TIMEOUT_MILLIS = 15000L
        const val REQUEST_TIMEOUT_MILLIS = 15000L
    }

    val client = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.ALL
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    encodeDefaults = true
                    ignoreUnknownKeys = true
                    prettyPrint = true
                }
            )
        }
        install(HttpTimeout) {
            connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
            requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
        }
        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                if (response.status.isSuccess().not()) {
                    val code = response.body<String>()

                    val error = runCatching {
                        Json.decodeFromString<AppException>(code)
                    }.getOrNull()

                    error?.let {
                        throw ServerException(it)
                    } ?: run {
                        throw ServerException(AppException(false, code, 500))
                    }
                }
            }
        }
    }

    fun getUrlString(path: String) = baseUrlHolder.url + path

    suspend inline fun <reified T> get(
        path: String,
        params: Map<String, Any>? = null
    ): T {
        val data = client.get(getUrlString(path)) {
            contentType(ContentType.Application.Json)
            params?.forEach { parameter(it.key, it.value) }
        }
        return data.body()
    }
}