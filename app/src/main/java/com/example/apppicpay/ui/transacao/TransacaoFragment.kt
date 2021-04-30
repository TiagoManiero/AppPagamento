package com.example.apppicpay.ui.transacao

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.apppicpay.R
import com.example.apppicpay.data.BandeiraCartao
import com.example.apppicpay.data.CartaoCredito
import com.example.apppicpay.data.Transacao
import com.example.apppicpay.data.UsuarioLogado
import com.example.apppicpay.extension.formatar
import com.example.apppicpay.ui.component.ComponentViewModel
import com.example.apppicpay.ui.component.Componente
import kotlinx.android.synthetic.main.fragment_transacao.*
import kotlinx.android.synthetic.main.item_contato.*
import kotlinx.android.synthetic.main.item_contato.textViewNome
import kotlinx.android.synthetic.main.item_contato.textViewNomeCompleto
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class TransacaoFragment: Fragment() {
    private val componentesViewModel: ComponentViewModel by sharedViewModel()
    private val argumentos by navArgs<TransacaoFragmentArgs>()
    private val transacaoViewModel: TransacaoViewModel by viewModel()
    private val usuario by lazy { argumentos.usuario }
    private val controlador by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transacao, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponente = Componente(bottomNavigation = false)
        configurarUsuario()
        configurarRadioButton()
        configurarBotaoTransferir()
        observarTransacao()
    }

    private fun observarTransacao() {
        transacaoViewModel.transacao.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            val direcao = TransacaoFragmentDirections.actionTransacaoFragmentToNavigationPagar()
            controlador.navigate(direcao)
        })
    }

    private fun configurarBotaoTransferir() {
        buttonTransferir.setOnClickListener {
            val isCartaoCredito = radioButtonCartaoCredito.isChecked
            val valor = getValor()
            val transacao = if (isCartaoCredito) {
                criarTransferenciaCartao(isCartaoCredito, valor)
            } else {
                criarTransferenciaSaldo(valor)
            }
            transacaoViewModel.transferir(transacao)
        }
    }

    private fun criarTransferenciaSaldo(valor: Double): Transacao {
        return Transacao(
            Transacao.gerarHash(),
            UsuarioLogado.usuario,
            usuario,
            Calendar.getInstance().formatar(),
            false,
            valor
        )
    }

    private fun getValor(): Double {
        val valor = editTextValor.text.toString()
        return if(valor.isEmpty()){
            0.0
        } else{
            valor.toDouble()
        }
    }

    private fun criarTransferenciaCartao(cartaoCredito: Boolean, valor: Double): Transacao {
        return Transacao(
            Transacao.gerarHash(),
            UsuarioLogado.usuario,
            usuario,
            Calendar.getInstance().formatar(),
            cartaoCredito,
            valor,
            CriarCartaoCredito()
        )
    }

    private fun CriarCartaoCredito(): CartaoCredito {
        val numeroCartao = editTextNumeroCartao.text.toString()
        val titular = editTextTitular.text.toString()
        val dataExpiracao = editTextVencimento.text.toString()
        val cvv = editTextCVV.text.toString()
        return CartaoCredito(
            BandeiraCartao.VISA,
            numeroCartao,
            titular,
            dataExpiracao,
            cvv,
            usuario = UsuarioLogado.usuario
        )
    }

    private fun configurarUsuario() {
        textViewNome.text = usuario.login
        textViewNomeCompleto.text = usuario.nomeCompleto
    }

    private fun configurarRadioButton() {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioButtonCartaoCredito -> {
                    constraintLayoutCartaoCredito.visibility = View.VISIBLE
                }
                R.id.radioButtonSaldo -> {
                    constraintLayoutCartaoCredito.visibility = View.INVISIBLE
                }
            }
        }
    }
}