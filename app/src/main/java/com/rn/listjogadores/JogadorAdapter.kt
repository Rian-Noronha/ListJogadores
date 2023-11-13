package com.rn.listjogadores

import android.content.Context
import android.content.res.TypedArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.rn.listjogadores.databinding.ItemJogadorBinding

class JogadorAdapter(
    private val ctx:Context,
    private val jogadores:List<Jogador>
): BaseAdapter() {

    private val logos:TypedArray by lazy {
        ctx.resources.obtainTypedArray(R.array.logos)
    }

    override fun getCount(): Int = jogadores.size

    override fun getItem(position: Int) = jogadores[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val binding:ItemJogadorBinding
        val jogador = jogadores[position]

        if(convertView == null){
            binding = ItemJogadorBinding.inflate(LayoutInflater.from(ctx), parent, false)
        }else{
            binding = ItemJogadorBinding.bind(convertView)
        }

        val holder = ViewHolder(binding)

        holder.imgLogo.setImageDrawable(logos.getDrawable(jogador.id))
        holder.txtNome.text = jogador.nome
        holder.txtPosicao.text = jogador.posicao
        holder.txtTime.text = jogador.time


        return binding.root
    }

    companion object{
        data class ViewHolder(val binding:ItemJogadorBinding){
            val imgLogo:ImageView = binding.imgLogo
            val txtNome: TextView = binding.txtNome
            val txtPosicao:TextView = binding.txtPosicao
            val txtTime:TextView = binding.txtTime
        }
    }


}