package com.example.apppicpay.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.apppicpay.R
import com.example.apppicpay.data.Usuario
import com.example.apppicpay.data.UsuarioLogado
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
    }

    private fun configurarBotaoLogin() {
        buttonLogin.setOnClickListener {
            UsuarioLogado.usuario = Usuario("tiago")
            vaiParaHome()
        }
    }

    private fun vaiParaHome() {
        val direcao = LoginFragmentDirections.actionGlobalLoginFragment()
        val controlador = findNavController()
        controlador.navigate(direcao)
    }
}