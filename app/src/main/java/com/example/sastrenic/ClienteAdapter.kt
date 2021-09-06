package com.example.sastrenic

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.cliente_list.view.*

class ClienteAdapter(private val mContex: Context, private val listaCliente: List<Cliente>) :
    ArrayAdapter<Cliente>(mContex, 0, listaCliente) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layout = LayoutInflater.from(mContex).inflate(R.layout.cliente_list, parent, false)
        val cliente = listaCliente[position]
        layout.nombre.text = cliente.nombre
        layout.direccion.text = cliente.direccion
        layout.telefono.text = "${cliente.telefono}"


        return layout
    }

}