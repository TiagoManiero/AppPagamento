package com.example.apppicpay.service


import com.example.apppicpay.data.UsuarioLogado
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val URL = "http://10.0.0.112:8080"

object RetrofitService {

    inline fun <reified T> create() = service.create(T::class.java)

    val service: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .client(criarHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private fun criarHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor { chain ->
                val request = aplicarToken(chain)
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.SECONDS)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()
    }

    private fun aplicarToken(chain: Interceptor.Chain): Request {
        if(UsuarioLogado.isUsuarioLogado()){
            val token = UsuarioLogado.token
            return chain.request()
                .newBuilder()
                .addHeader("Authorization","${token.tipo} ${token.token}")
                .build()
        } else {
            return chain.request()
        }

    }


}