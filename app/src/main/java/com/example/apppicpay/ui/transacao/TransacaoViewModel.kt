package com.example.apppicpay.ui.transacao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppicpay.data.Transacao
import com.example.apppicpay.service.ApiService
import kotlinx.coroutines.launch

class TransacaoViewModel(private val apiService: ApiService): ViewModel() {
    private val _transacao = MutableLiveData<Transacao>()
    val transacao: LiveData<Transacao> = _transacao

    fun transferir(transacao: Transacao) {
        viewModelScope.launch {
            try {
                _transacao.value = apiService.realizarTransacao(transacao)
            }catch (e: Exception){
                Log.e("erro",e.message ?: "")
            }

        }
    }
}