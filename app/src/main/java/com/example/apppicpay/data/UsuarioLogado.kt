package com.example.apppicpay.data

object UsuarioLogado {
    lateinit var usuario: Usuario
    lateinit var token: Token

    fun isUsuarioLogado(): Boolean = this::token.isInitialized

    fun isNaoLogado(): Boolean = !isUsuarioLogado()

    fun setSaldo(saldo: Double) {
        usuario.saldo = saldo
    }
}