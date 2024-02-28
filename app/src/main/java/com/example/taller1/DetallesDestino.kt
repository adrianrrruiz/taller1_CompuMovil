package com.example.taller1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.taller1.model.Destino
import com.example.taller1.model.WeatherResponse
import com.example.taller1.services.IWeatherService
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.Toast

class DetallesDestino : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_destino)

        val botonFavoritos = findViewById<Button>(R.id.favoritos)

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

        botonFavoritos.setOnClickListener {
            val destino = // obtener el destino correspondiente
                MainActivity.destinosFavoritos.add(destino)
            Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show()
        }
        getWeather(destino.pais)

    }
    private fun getWeather(city: String) {
        val temperaturaTxt = findViewById<TextView>(R.id.temperatura)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(IWeatherService::class.java)
        val call = service.getCurrentWeather(city, "7dc64ee5f1934d7cabe193311242702")

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherData = response.body()
                    if (weatherData != null) {
                        temperaturaTxt.text = weatherData.current.temp_c.toString() + " °C"
                    }
                } else {
                    // Manejar casos de respuesta no exitosa, como errores 4xx/5xx
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Aquí manejas errores de red o problemas de serialización
            }
        })
    }
}
