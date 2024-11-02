package com.flexath.ecommercemobile.data.remote.api.ktor

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import javax.inject.Inject

class ProductKtorClient @Inject constructor() {

    @OptIn(ExperimentalSerializationApi::class)
    fun getHttpClient() = HttpClient(Android) {

        install(JsonFeature) {
            serializer = KotlinxSerializer(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })

            engine {
                connectTimeout = TIME_OUT
                socketTimeout = TIME_OUT
            }
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v(TAG_KTOR_LOGGER, message)
                }

            }
            level = LogLevel.ALL
        }

        install(ResponseObserver) {
            onResponse { response ->
                Log.d(TAG_HTTP_STATUS_LOGGER, "${response.status.value} : ${response.status.description}")
            }
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
//            parameter("api_key", ApiConstants.API_KEY)
        }
    }

    companion object {
        private const val TIME_OUT = 10_000
        private const val TAG_KTOR_LOGGER = "ktor_logger:"
        private const val TAG_HTTP_STATUS_LOGGER = "http_status:"
    }
}