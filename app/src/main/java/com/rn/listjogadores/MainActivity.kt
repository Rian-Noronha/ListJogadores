package com.rn.listjogadores

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.rn.listjogadores.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var txtFooter:TextView
    private val jogadores = mutableListOf(
        Jogador("Rian", "Meia", "Borussia", 0),
        Jogador("Dudé", "Meia", "Borussia", 1),
        Jogador("Ermes", "Meia", "Borussia", 2),
        Jogador("Eurico", "Meia", "Borussia", 3),
        Jogador("Leonardo", "Centroavante", "Borussia", 4),
        Jogador("Ramiro", "Zagueiro", "Borussia", 5),
        Jogador("Kita", "Zagueiro", "Borussia", 6),
        Jogador("Antônio", "Goleiro", "Borussia", 7),
        Jogador("Edvan", "Meia", "Borussia", 8),
        Jogador("Filipy", "Meia", "Borussia", 9)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.listView.emptyView = binding.empty
        val adapterJogadores = JogadorAdapter(this, jogadores)
        binding.listView.adapter = adapterJogadores

        initHeaderAndFooter(binding.listView, adapterJogadores)
        binding.listView.setOnItemClickListener{parent, _, positon, _ ->
            val jogador = parent.getItemAtPosition(positon)as? Jogador
            if(jogador != null){
                val (nome, posicao, time) = jogador
                Toast.makeText(this, "$nome $posicao $time", Toast.LENGTH_SHORT).show()
                jogadores.remove(jogador)
                adapterJogadores.notifyDataSetChanged()
                txtFooter.text = resources.getQuantityString(R.plurals.footer_text, adapterJogadores.count, adapterJogadores.count)
            }
        }


    }


    private fun initHeaderAndFooter(listView: ListView, adapter:JogadorAdapter){

        val padding = 8
        val txtHeader = TextView(this)
        txtHeader.setBackgroundColor(Color.GRAY)
        txtHeader.setTextColor(Color.WHITE)
        txtHeader.setText(R.string.header_text)
        txtHeader.setPadding(padding, padding, 0, padding)
        listView.addHeaderView(txtHeader)

        txtFooter = TextView(this)
        txtFooter.text = resources.getQuantityString(
            R.plurals.footer_text, adapter.count, adapter.count)
        txtFooter.setBackgroundColor(Color.LTGRAY)
        txtFooter.gravity = Gravity.END
        txtFooter.setPadding(0, padding, padding, padding)
        listView.addFooterView(txtFooter)

    }

}