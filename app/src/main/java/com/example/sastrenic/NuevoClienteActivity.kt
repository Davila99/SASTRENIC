package com.example.sastrenic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_nuevo_cliente.*
import kotlinx.android.synthetic.main.cliente_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoClienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_cliente)

        et_fecha_recepcion.setOnClickListener { showDatePickerDialog() }
        et_fecha_entregua.setOnClickListener { showDatePickerDialog2() }

        var idCliente: Int? = null

        if (intent.hasExtra("cliente")) {

            val cliente = intent.extras?.getSerializable("cliente") as Cliente

            et_nombre.setText(cliente.nombre)
            et_cedula.setText(cliente.cedula)
            et_direccion.setText(cliente.direccion)
            et_telefono.setText(cliente.telefono.toString())
            et_fecha_recepcion.setText(cliente.fecha_recepcion)
            et_fecha_entregua.setText(cliente.fecha_entrega)
            et_descripcion.setText(cliente.descripcion_prenda)
            et_cantidad.setText(cliente.cantidad.toString())
            et_precio.setText(cliente.precio.toString())
            idCliente = cliente.idCliente
        }

        val database = AppDatabase.getDatabase(this)

        btn_guadar.setOnClickListener {

            val nombre = et_nombre.text.toString()
            val cedula = et_cedula.text.toString()
            val direccion = et_direccion.text.toString()
            val telefono = et_telefono.text.toString().toInt()
            val fecha_recepcion = et_fecha_recepcion.text.toString()
            val fecha_entrega = et_fecha_entregua.text.toString()
            val descripcion = et_descripcion.text.toString()
            val cantidad = et_cantidad.text.toString().toInt()
            val precio = et_precio.text.toString().toDouble()
            val total = cantidad * precio

            val cliente = Cliente(
                nombre,
                cedula,
                direccion,
                telefono,
                fecha_recepcion,
                fecha_entrega,
                descripcion,
                cantidad,
                precio,
                total
            )
            if (nombre.isEmpty() || cedula.isEmpty() || direccion.isEmpty() || telefono.toString()
                    .isEmpty() || fecha_recepcion.isEmpty() || fecha_entrega.isEmpty() || descripcion.isEmpty() || cantidad.toString()
                    .isEmpty() || precio.toString().isEmpty()
            ) {
                Toast.makeText(this, "Se requiere todos los campos", Toast.LENGTH_LONG).show()

            } else {

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


    private fun showDatePickerDialog() {
        val datePicker =
            DatePickerFragment { day, month, year -> onDateSelecter(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun showDatePickerDialog2() {

        val datePicker2 =
            DatePickerFragment { day, month, year -> onDateSelecter2(day, month, year) }
        datePicker2.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelecter(day: Int, month: Int, year: Int) {
        et_fecha_recepcion.setText("$day/ $month/ $year")
    }

    fun onDateSelecter2(day: Int, month: Int, year: Int) {
        et_fecha_entregua.setText("$day/ $month/ $year")
    }

}