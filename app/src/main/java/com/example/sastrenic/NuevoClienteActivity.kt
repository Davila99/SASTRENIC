package com.example.sastrenic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_nuevo_cliente.*
import kotlinx.android.synthetic.main.cliente_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_cliente)

        var idCliente: Int? = null

        if (intent.hasExtra("cliente")) {

            val cliente = intent.extras?.getSerializable("cliente") as Cliente

            et_nombre.setText(cliente.nombre)
            et_cedula.setText(cliente.cedula)
            et_telefono.setText(cliente.telefono.toString())
            et_fecha.setText(cliente.fecha_recepcion)
            idCliente = cliente.idCliente
        }

        val database = AppDatabase.getDatabase(this)

        btn_guadar.setOnClickListener {

            val nombre = et_nombre.text.toString()
            val cedula = et_cedula.text.toString()
            val telefono = et_telefono.text.toString().toInt()
            val fecha = et_fecha.text.toString()

            val cliente = Cliente(nombre, cedula, telefono, fecha)

            if (idCliente != null) {

                CoroutineScope(Dispatchers.IO).launch {

                    cliente.idCliente = idCliente

                    database.clientes().update(cliente)

                    this@NuevoClienteActivity.finish()
                }
            } else {

                CoroutineScope(Dispatchers.IO).launch {

                    database.clientes().insertAll(cliente)

                    this@NuevoClienteActivity.finish()
                }


            }

        }
    }
}