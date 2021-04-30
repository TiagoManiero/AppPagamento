package com.example.apppicpay.data

object UsuarioLogado {
    lateinit var usuario: Usuario

    fun isUsuarioLogado(): Boolean = this::usuario.isInitialized

    fun isNaoLogado(): Boolean = !isUsuarioLogado()
}