package com.example.apppicpay.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.apppicpay.R
import com.example.apppicpay.data.Login
import com.example.apppicpay.data.State
import com.example.apppicpay.extension.desaparecer
import com.example.apppicpay.extension.esconder
import com.example.apppicpay.extension.getString
import com.example.apppicpay.extension.mostrar
import com.example.apppicpay.ui.component.ComponentViewModel
import com.example.apppicpay.ui.component.Componente
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment: Fragment(R.layout.fragment_login) {
    private val viewModel: LoginViewModel by viewModel()
    private val componentViewModel: ComponentViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentViewModel.temComponente = Componente(bottomNavigation = false)
        configurarBotaoLogin()
        estadosUsuario()
    }

    private fun estadosUsuario() {
        viewModel.usuarioState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                is State.Loading -> {
                    progressBar.mostrar()
                }
                is State.Success -> {
                    progressBar.desaparecer()
                    vaiParaHome()
                }
                is State.Error -> {
                    progressBar.desaparecer()
                    Toast.makeText(requireContext(), "Falha ao autenticar", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun vaiParaHome() {
        val direcao = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
        findNavController().navigate(direcao)
    }

    private fun configurarBotaoLogin() {
        buttonLogin.setOnClickListener {
            val usuario = textInputLayoutUsuario.getString()
            val senha = textInputLayoutSenha.getString()
            val login = Login(usuario, senha)
            viewModel.login(login)
        }
    }
}