package com.example.sastrenic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun startAplication(view: View) {

        var usuario = findViewById<EditText>(R.id.editUsuario).text.toString();
        var contraseña = findViewById<EditText>(R.id.editContraseña).text.toString();
        if (usuario == "eliseo" && contraseña == "1234") {
            val intent = Intent(this, InicioActivity::class.java)
            startActivity(intent)
            val toast = Toast.makeText(this, "Bienvenido a SASTRENIC", Toast.LENGTH_LONG)
            toast.show()

        } else {
            val toast = Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_LONG)
            toast.show()
        }
    }
}
