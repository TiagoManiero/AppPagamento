package com.example.apppicpay

import android.app.Application
import com.example.apppicpay.di.repositoryModule
import com.example.apppicpay.di.serviceModule
import com.example.apppicpay.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppAplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppAplication)
            modules(viewModelModule, serviceModule, repositoryModule)
        }
    }
}