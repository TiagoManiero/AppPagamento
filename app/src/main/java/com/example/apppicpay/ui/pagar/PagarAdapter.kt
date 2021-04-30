package com.example.apppicpay.ui.pagar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apppicpay.R
import com.example.apppicpay.data.Usuario
import kotlinx.android.synthetic.main.item_contato.view.*

class PagarAdapter(private val usuarios: List<Usuario>, private val onClick: (Any?) -> Unit):
    RecyclerView.Adapter<PagarAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(usuario: Usuario){
            with(itemView){
                textViewNomeCompleto.text = usuario.nomeCompleto
                textViewNome.text = usuario.login
                setOnClickListener {
                    onClick(usuario)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contato, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val usuario = usuarios[position]
        holder.bind(usuario)
    }

    override fun getItemCount(): Int {
        return usuarios.size
    }
}