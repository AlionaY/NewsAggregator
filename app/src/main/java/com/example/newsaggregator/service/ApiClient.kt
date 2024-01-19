package com.example.newsaggregator.service

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class ApiClient(private val baseUrlHolder: BaseUrlHolder) {

    companion object {
        const val CONNECT_TIMEOUT_MILLIS = 10000L
        const val REQUEST_TIMEOUT_MILLIS = 10000L
    }

    val client = HttpClient(CIO) {
        Logging {
            level = LogLevel.ALL
        }
        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
        install(HttpTimeout) {
            connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
            requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
        }

        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                if (response.status.value >= 400) {
                    val raw = response.body<String>()
                    val error = runCatching {
                        Json.decodeFromString<AppException>(raw)
                    }.getOrNull()

                    Log.d("####", "response error $error")

                    error?.let {
                        throw ServerException(it)
                    } ?: run {
                        throw ServerException(AppException(false, raw, 500))
                    }
                }
            }
        }
    }

    fun getUrlString(path: String) = baseUrlHolder.url + path

    suspend inline fun <reified T> get(
        path: String,
        params: Map<String, Any>? = null
    ): T = client.get(getUrlString(path)) {
        contentType(ContentType.Application.Json)
        params?.forEach { parameter(it.key, it.value)}
    }.body()
}