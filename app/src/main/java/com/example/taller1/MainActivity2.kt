package com.example.taller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val json = JSONObject(loadJSONFromAsset())
        val destinosJsonArray = json.getJSONArray("destinos")
        val nombreDestinos = ArrayList<String>()

        for (i in 0 until destinosJsonArray.length()){
            val jsonObject = destinosJsonArray.getJSONObject(i)
            val nombre = jsonObject.getString("nombre")
            nombreDestinos.add((nombre))
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombreDestinos)
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = adapter
    }

    fun loadJSONFromAsset(): String?{
        var json: String? = null
        try {
            val istream: InputStream = assets.open("destinos.json")
            val size: Int = istream.available()
            val buffer = ByteArray(size)
            istream.read(buffer)
            istream.close()
            json = String(buffer, Charsets.UTF_8)
        }
        catch (ex: IOException){
            ex.printStackTrace()
            return null
        }
        return json
    }
}