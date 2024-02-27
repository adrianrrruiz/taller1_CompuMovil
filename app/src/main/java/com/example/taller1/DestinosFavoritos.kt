package com.example.taller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class DestinosFavoritos : AppCompatActivity() {
    val destinosFavoritos = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destinos_favoritos)

        val datosGuardados = savedInstanceState?.getStringArrayList("destinosFavoritos")
        datosGuardados?.let {
            destinosFavoritos.addAll(it)
        }

        val nombreDestino = intent.getStringExtra("nombreDestino")

        nombreDestino?.let {
            destinosFavoritos.add(it)
            actualizarDestinosFavoritos()
        }

    }
    private fun actualizarDestinosFavoritos (){
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, destinosFavoritos)
        val listView = findViewById<ListView>(R.id.destinosFav)
        listView.adapter = adapter
    }

    fun guardarLista (outState: Bundle){
        outState.putStringArrayList("destinosFavoritos", destinosFavoritos)
        super.onSaveInstanceState(outState)
    }

}