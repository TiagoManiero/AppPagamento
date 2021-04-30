package com.example.apppicpay.ui.pagar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.apppicpay.R
import com.example.apppicpay.data.Usuario
import com.example.apppicpay.ui.component.ComponentViewModel
import com.example.apppicpay.ui.component.Componente
import kotlinx.android.synthetic.main.fragment_pagar.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PagarFragment : Fragment() {
    private val componentesViewModel: ComponentViewModel by sharedViewModel()
    private val controlador by lazy { findNavController() }
    private val pagarViewModel: PagarViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pagar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponente = Componente(bottomNavigation = true)
        observarContatos()
    }

    private fun observarContatos() {
        pagarViewModel.contatos.observe(viewLifecycleOwner, Observer {
            it?.let { usuarios ->
                configurarRecyclerView(usuarios)
            }
        })
    }

    private fun configurarRecyclerView(usuarios: List<Usuario>) {

        recyclerView.adapter = PagarAdapter(usuarios) { usuario ->
            transacao(usuario as Usuario)
        }
    }

    private fun transacao(usuario: Usuario) {
        val direcao = PagarFragmentDirections.actionNavigationPagarToTransacaoFragment(usuario)
        controlador.navigate(direcao)
    }
}