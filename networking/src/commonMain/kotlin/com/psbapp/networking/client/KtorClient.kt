package com.psbapp.networking.client

import com.psbapp.networking.datastore.NetworkInfo
import com.psbapp.networking.utils.KtorConstants
import com.psbapp.networking.utils.KtorConstants.NetworkApiConfig.CONNECT_TIMEOUT_MILLIS
import com.psbapp.networking.utils.KtorConstants.NetworkApiConfig.REQUEST_TIMEOUT_MILLIS
import com.psbapp.networking.utils.KtorConstants.NetworkApiConfig.SOCKET_TIMEOUT_MILLIS
import com.psbapp.networking.utils.LogoutType
import com.psbapp.networking.utils.apiHeaders
import com.psbapp.networking.utils.defaultHttpRetryConfigs
import com.psbapp.networking.utils.networkLogger
import com.psbapp.utils.EMPTY
import com.psbapp.utils.dispatcher.DispatcherProvider
import com.psbapp.utils.json.JSON
import com.psbapp.utils.logger.Print
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.HttpRequestRetryConfig
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.KotlinxWebsocketSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.serialization.json.Json

class KtorClient(
    private val networkInfo: NetworkInfo,
    private val dispatcher: DispatcherProvider,
    private val urlProtocol: URLProtocol = URLProtocol.HTTPS,
    private val refreshTokenPlugin: HttpClientConfig<*>.() -> Unit = {},
    private val jsonConfigs: Json = JSON,
    private val httpClientEngine: HttpClientEngine = getHttpClientEngine(),
    private val httpRetryConfigs: HttpRequestRetryConfig.() -> Unit = {
        defaultHttpRetryConfigs()
    },
) {

    companion object {
        var logoutFlow: MutableSharedFlow<Pair<Unit, LogoutType>> = MutableSharedFlow()
        val apiFailedReqQ: ArrayDeque<Pair<HttpRequestBuilder.() -> Unit, Int>> = ArrayDeque()
    }

    fun getHttpClient(enableNetworkLogs: Boolean) = HttpClient(engine = httpClientEngine) {
        install(WebSockets) {
            pingIntervalMillis = 20_000
            contentConverter = KotlinxWebsocketSerializationConverter(Json)
        }

        install(ContentNegotiation) { json(json = jsonConfigs) }
        install(Auth) {
            bearer {
                loadTokens {
                    val token = networkInfo.getAccessToken()
                    if (token.isNotEmpty()) {
                        BearerTokens(accessToken = token, refreshToken = String.EMPTY)
                    } else {
                        null
                    }
                }
            }
        }
        install(HttpRequestRetry) { httpRetryConfigs() }
        install(DefaultRequest) {
            apply {
                url {
                    // Protocol is also configurable please reach-kmm team for change
                    // requests.
                    protocol = urlProtocol
                    host = networkInfo.getBaseUrl()
                }

                headers {
                    apiHeaders(
                        block = {
                            header(
                                KtorConstants.AuthHeader.X_API_KEY,
                                networkInfo.getXApiKey()
                            )
                            header(
                                "x-device-type",
                                "mobile"
                            )
                            header(
                                "x-app-version",
                                networkInfo.getAppVersion()
                            )
                        })
                }
            }
        }
        // Auth plugin to refresh access-token
        refreshTokenPlugin()
        install(HttpTimeout) {
            requestTimeoutMillis = REQUEST_TIMEOUT_MILLIS
            connectTimeoutMillis = CONNECT_TIMEOUT_MILLIS
            socketTimeoutMillis = SOCKET_TIMEOUT_MILLIS
        }

        // Log HTTP request and response
        if (enableNetworkLogs) {
            install(Logging) { networkLogger() }
        }

        install(ResponseObserver) {
            onResponse { response ->
                println("AppDebug HTTP ResponseObserver status: ${response}")
            }
        }
        HttpResponseValidator {
            validateResponse { response: HttpResponse ->
                val statusCode = response.status.value

                if (statusCode == 401) {
                    logoutFlow.emit(
                        Pair(
                            first = Unit,
                            LogoutType.SYSTEM_GENERATED
                        )
                    )
                }
            }

            handleResponseExceptionWithRequest { cause, request ->
                Print.log("handleResponseExceptionWithRequest : ${cause.message}")
                throw cause
            }
        }

        /* install(JsonFeature){
            serializer = KotlinxSerializer(json)
        }*/

    }
}
