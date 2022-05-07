package com.example.fragment

import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {
    val menuFragment: Menu = Menu()

    val REQUEST_CODE: Int = 200

    /**
     * Función onCreate
     */
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        soporteF=supportFragmentManager
        soporteF.beginTransaction().replace(R.id.containerActivity, menuFragment).commit()
        verificarPermisos()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun verificarPermisos() {
        val permisoEscribir: Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val permisoLeer: Int = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if(permisoEscribir == PackageManager.PERMISSION_GRANTED && permisoLeer == PackageManager.PERMISSION_GRANTED){

            println("Permisos de lectura y escritura concedidos")
        } else {
            requestPermissions(listOf<String>(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE).toTypedArray(), REQUEST_CODE)
        }
    }
    companion object{
        lateinit var soporteF:FragmentManager
    }
}