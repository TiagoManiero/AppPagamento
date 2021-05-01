package com.example.apppicpay.repository

import com.example.apppicpay.data.*
import com.example.apppicpay.service.ApiService

interface TransacaoRepository {
    suspend fun getSaldo(login: String): Usuario

    suspend fun getTransacoes(login: String): List<Transacao>
}


