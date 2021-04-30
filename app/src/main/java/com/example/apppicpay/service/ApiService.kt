package com.example.apppicpay.service

import com.example.apppicpay.data.PageTransacao
import com.example.apppicpay.data.Transacao
import com.example.apppicpay.data.Usuario
import retrofit2.http.*

interface ApiService {
    @GET("/usuarios/contato/{login}")
    suspend fun getContatos(@Query("login") login: String): List<Usuario>

    @GET("/usuarios/{login}/saldo")
    suspend fun getSaldo(@Path("login") login: String): Usuario

    @POST("/transacoes")
    suspend fun realizarTransacao(@Body transacao: Transacao): Transacao

    @GET("/transacoes")
    suspend fun getTransacoes(@Query("login") login: String): PageTransacao

}