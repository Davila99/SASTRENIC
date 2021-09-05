package com.example.sastrenic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_inicio.*

class InicioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        inicio();
    }

    fun inicio() {

        var listaclientes = emptyList<Cliente>()

        val database = AppDatabase.getDatabase(this)

        database.clientes().getAll().observe(this, Observer {
            listaclientes = it

            val adapter = ClienteAdapter(this, listaclientes)

            lista.adapter = adapter

        })



        lista.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, ClienteActivity::class.java)
            intent.putExtra("id", listaclientes[position].idCliente)
            startActivity(intent)
        }

        floatingActionButton.setOnClickListener {
            val intent = Intent(this, NuevoClienteActivity::class.java)
            startActivity(intent)
        }

    }

}