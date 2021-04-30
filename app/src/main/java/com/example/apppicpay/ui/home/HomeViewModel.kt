package com.example.apppicpay.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppicpay.data.PageTransacao
import com.example.apppicpay.data.Transacao
import com.example.apppicpay.data.Usuario
import com.example.apppicpay.data.UsuarioLogado
import com.example.apppicpay.service.ApiService
import kotlinx.coroutines.launch

class HomeViewModel(private val apiService: ApiService) : ViewModel() {
    private val _saldo = MutableLiveData<Double>()
    val saldo: LiveData<Double> = _saldo
    private val _transacoes = MutableLiveData<List<Transacao>>()
    val transacoes: LiveData<List<Transacao>> = _transacoes
    val onError = MutableLiveData<String>()
    val onLoading = MutableLiveData<Boolean>()

    init {
        onLoading.value = true
        viewModelScope.launch {
            try {
                val login = UsuarioLogado.usuario.login
                val saldo = apiService.getSaldo(login).saldo
                _transacoes.value = apiService.getTransacoes(login)
            } catch (e: Exception) {
                onError.value = e.message
            }
            onLoading.value = false
        }

    }
}