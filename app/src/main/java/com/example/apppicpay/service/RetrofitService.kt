package com.example.apppicpay.service


import okhttp3.OkHttpClient
import okhttp3.internal.Internal.instance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

private const val URL = "http://10.0.0.112:8080"

object RetrofitService {

    inline fun <reified T> create() = service.create(T::class.java)

    val service: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


}