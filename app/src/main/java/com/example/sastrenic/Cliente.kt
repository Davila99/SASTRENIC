package com.example.sastrenic

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "clientes")
class Cliente(
    val nombre: String,
    val cedula: String,
    val telefono: Int,
    val fecha_recepcion: String,
    @PrimaryKey(autoGenerate = true)
    var idCliente: Int = 0
) : Serializable