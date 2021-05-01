package com.example.apppicpay.ui.login


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppicpay.data.*
import com.example.apppicpay.service.ApiService
import kotlinx.coroutines.launch

class LoginViewModel(private val apiService: ApiService): ViewModel() {
    val usuarioState = MutableLiveData<State<Usuario>>()
    private val _usuario = MutableLiveData<Usuario>()
    val usuario: LiveData<Usuario> = _usuario
    val onLoading = MutableLiveData<Boolean>()
    val onError = MutableLiveData<Exception>()

    fun login(login: Login){
        viewModelScope.launch {
            usuarioState.value = State.Loading()
            try {
                val token = apiService.autenticar(login)
                UsuarioLogado.token = token
                apiService.getUsuario(login.usuario)
                UsuarioLogado.usuario = usuario as Usuario
                usuarioState.value = State.Success(usuario)
            } catch (e: Exception){
                usuarioState.value = State.Error(e)
            }
            onLoading.value = false
        }
    }
}