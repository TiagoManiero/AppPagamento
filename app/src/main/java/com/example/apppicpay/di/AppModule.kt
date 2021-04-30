package com.example.apppicpay.di

import com.example.apppicpay.service.ApiService
import com.example.apppicpay.service.RetrofitService
import com.example.apppicpay.ui.component.ComponentViewModel
import com.example.apppicpay.ui.pagar.PagarViewModel
import com.example.apppicpay.ui.home.HomeViewModel
import com.example.apppicpay.ui.ajuste.AjusteViewModel
import com.example.apppicpay.ui.transacao.TransacaoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module{
    viewModel { HomeViewModel(get()) }
    viewModel { PagarViewModel(get()) }
    viewModel { AjusteViewModel() }
    viewModel { ComponentViewModel() }
    viewModel { TransacaoViewModel(get()) }
}

val serviceModule = module {
    single { RetrofitService.create<ApiService>() }
}