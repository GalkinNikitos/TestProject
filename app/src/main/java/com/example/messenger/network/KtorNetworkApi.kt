package com.example.messenger.network

import android.util.Log
import com.example.messenger.data.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds

interface KtorNetworkApi {
    suspend fun getUsers(pageNumber: Int): List<ApiResponse>
}

private const val NETWORK_BASE_URL = "www.anapioficeandfire.com"

class KtorNetwork : KtorNetworkApi {

    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val client: HttpClient by lazy {
        HttpClient(engine = OkHttp.create()) {
            install(ContentNegotiation) { json(json) }
            install(HttpTimeout) {
                connectTimeoutMillis = 20.seconds.inWholeMilliseconds
                requestTimeoutMillis = 60.seconds.inWholeMilliseconds
                socketTimeoutMillis = 20.seconds.inWholeMilliseconds
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }

    override suspend fun getUsers(pageNumber: Int): List<ApiResponse> {
        return try {
            client.get {
                url {
                    host = NETWORK_BASE_URL
                    protocol = URLProtocol.HTTPS
                    contentType(ContentType.Application.Json)
                    path("api", "characters")
                    parameters.append("page", pageNumber.toString())
                    parameters.append("pageSize", "50")
                }
            }.let { response ->
                Log.d("Ktor Response", response.body())
                response.body()
            }
        } catch (exception: Exception) {
            Log.e("Error", exception.message.toString())
            listOf()
        }
    }
}
