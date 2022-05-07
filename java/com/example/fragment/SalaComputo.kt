package com.example.fragment

import android.content.Context
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class SalaComputo : Fragment() {

    lateinit var claveC: EditText
    lateinit var nombreSa: EditText
    lateinit var agregarSala: ExtendedFloatingActionButton
    lateinit var buscarSala: ExtendedFloatingActionButton
    val ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista=inflater.inflate(R.layout.fragment_sala_computo, container, false)
        inicializar(vista)
        click()
        return vista
    }
    fun inicializar(view: View){
        claveC= view.findViewById(R.id.claveComputo)
        nombreSa=view.findViewById(R.id.nombreSala)
        agregarSala= view.findViewById(R.id.agregarSala)
        buscarSala= view.findViewById(R.id.buscarSala)
    }
    fun info(): String {
        var datos: String = ""
        datos += "Clave:${claveC.text.toString()}," +
                "Nombre Sala:${nombreSa.text.toString()}"

        return datos

    }
    fun click(){
        agregarSala.setOnClickListener {
            escribirArchivo("Sala_Computo",info())
        }
        buscarSala.setOnClickListener {
            var leer:String=leerArchivo("Sala_Computo")
            lanzarConsulta(leer,"Sala Computo")
        }
    }
    fun lanzarConsulta(mensaje: String, titulo: String) {
        MaterialAlertDialogBuilder(activity as Context)
            .setTitle(titulo)
            .setMessage(mensaje).setNegativeButton("Cerrar") { dialog, which ->
                // Respond to negative button press
            }.show()
    }
    fun escribirArchivo(nombre: String, contenido: String) {
        val nombreArchivo = "$nombre.txt"
        val archivo = File(ruta + "/" + nombreArchivo)

        if (!archivo.exists()) {
            archivo.createNewFile()
            println(archivo.path)
        }

        val fileWrite: FileWriter = FileWriter(archivo, true)

        val escribir: BufferedWriter = BufferedWriter(fileWrite)
        escribir.write("$contenido")
        escribir.close()
        fileWrite.close()
        //view.Toast.makeText(view.context, "Se insertaron datos correctamente", Toast.LENGTH_SHORT).show()
    }
    fun leerArchivo(nombre: String): String {
        var contenido: String = ""
        val pathName: String = ruta + "/" + nombre
        val archivo = File(pathName)

        if (!archivo.exists()) {
            contenido = "No se han realizado Registros!"
        } else {
            contenido = archivo.readText()
        }

        return contenido
    }
}