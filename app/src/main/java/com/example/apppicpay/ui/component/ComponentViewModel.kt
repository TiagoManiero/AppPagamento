package com.example.apppicpay.ui.component

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ComponentViewModel: ViewModel() {
    val components = MutableLiveData<Componente>().also {
        it.value = temComponente
    }
    var temComponente = Componente()
        set(value){
            field = value
            components.value = value
        }
}

data class Componente(
    val bottomNavigation: Boolean = false
)