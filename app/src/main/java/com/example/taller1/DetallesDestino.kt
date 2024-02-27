package com.example.taller1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.taller1.model.Destino
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

class DetallesDestino : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_destino)

        val intent = intent
        val destinoSeleccionado = intent.getStringExtra("destinoSeleccionado")
        val gson = Gson()
        val destino = gson.fromJson(destinoSeleccionado, Destino::class.java)

        val nombreTxt = findViewById<TextView>(R.id.nombre)
        val paisTxt = findViewById<TextView>(R.id.pais)
        val categoriaTxt = findViewById<TextView>(R.id.categoria)
        val planTxt = findViewById<TextView>(R.id.plan)
        val precioTxt = findViewById<TextView>(R.id.precio)

        nombreTxt.text = destino.nombre
        paisTxt.text = destino.pais
        categoriaTxt.text = destino.categoria
        planTxt.text = destino.plan
        precioTxt.text = "USD " + destino.precio.toString()

    }
}