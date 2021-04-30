package com.example.apppicpay.ui.pagar

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppicpay.data.Usuario
import com.example.apppicpay.data.UsuarioLogado
import com.example.apppicpay.service.ApiService
import kotlinx.coroutines.launch

class PagarViewModel(private val apiService: ApiService) : ViewModel() {
    private val _contatos = MutableLiveData<List<Usuario>>()
    val contatos: LiveData<List<Usuario>> = _contatos

    init {
        viewModelScope.launch {
            try {
                val login = UsuarioLogado.usuario.login
                _contatos.value = apiService.getContatos(login)
            } catch (e: Exception){
                Log.e("erro",e.message ?: "")
            }

        }

    }

}