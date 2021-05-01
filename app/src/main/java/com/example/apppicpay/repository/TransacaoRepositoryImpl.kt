package com.example.apppicpay.repository

import com.example.apppicpay.data.*
import com.example.apppicpay.service.ApiService

class TransacaoRepositoryImpl(
    private val apiService: ApiService,
    private val transacaoDAO: TransacaoDAO
) : TransacaoRepository {

    override suspend fun getSaldo(login: String): Usuario = apiService.getSaldo(login)

    override suspend fun getTransacoes(login: String): List<Transacao> {
        val transacoes = apiService.getTransacoes(login).content.toModel()
        transacaoDAO.save(transacoes.toLocal())
        return transacoes
    }
}