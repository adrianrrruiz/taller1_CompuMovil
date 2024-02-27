package com.example.taller1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val botonExplorar = findViewById<Button>(R.id.button1)
        val botonFavoritos = findViewById<Button>(R.id.button2)
        val spinner = findViewById<Spinner>(R.id.spinner)
        var filtroSeleccionado = ""

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                // Obtener el valor seleccionado
                filtroSeleccionado = parent.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
                // Acción opcional en caso de que no se seleccione nada
            }
        }

        botonExplorar.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("filtroSeleccionado", filtroSeleccionado)
            startActivity(intent)
        }

        botonFavoritos.setOnClickListener {
            val intent2 = Intent (this, DestinosFavoritos::class.java)
            startActivity(intent2)
        }
    }
}