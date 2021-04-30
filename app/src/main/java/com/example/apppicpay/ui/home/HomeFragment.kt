package com.example.apppicpay.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.apppicpay.R
import com.example.apppicpay.data.UsuarioLogado
import com.example.apppicpay.extension.formatarMoeda
import com.example.apppicpay.ui.component.ComponentViewModel
import com.example.apppicpay.ui.component.Componente
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val componentesViewModel: ComponentViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(UsuarioLogado.isNaoLogado()) {
            vaiParaLogin()
        }
        componentesViewModel.temComponente = Componente(bottomNavigation = true)
        observarSaldo()
        observarErro()
        observarTransacoes()
        observarCarregando()
    }

    private fun observarCarregando() {
        homeViewModel.onLoading.observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        })
    }

    private fun observarTransacoes() {
        homeViewModel.transacoes.observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerView.adapter = HomeAdapter(it)
            }
        })
    }

    private fun observarErro() {
        homeViewModel.onError.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(this@HomeFragment.context, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observarSaldo() {
        homeViewModel.saldo.observe(viewLifecycleOwner, Observer {
            it.let { saldo ->
                textViewSaldo.text = saldo.formatarMoeda()
            }
        })
    }

    private fun vaiParaLogin() {
        val direcao = HomeFragmentDirections.actionGlobalLoginFragment()
        val controlador = findNavController()
        controlador.navigate(direcao)
    }
}