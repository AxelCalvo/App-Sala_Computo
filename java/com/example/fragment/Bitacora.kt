package com.example.fragment

import android.content.Context
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


class Bitacora : Fragment() {
    lateinit var fechaReg: EditText
    lateinit var numContrl: EditText
    lateinit var claveMa: EditText
    lateinit var agregarB: ExtendedFloatingActionButton
    lateinit var buscarB: ExtendedFloatingActionButton

    val ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_bitacora, container, false)
        inicializar(vista)
        return vista
    }

    fun inicializar(view: View) {

        fechaReg = view.findViewById(R.id.fechaRegistro)
        numContrl = view.findViewById(R.id.numControlBi)
        claveMa = view.findViewById(R.id.claveMateriaBi)
        agregarB = view.findViewById(R.id.agregarBitacora)
        buscarB = view.findViewById(R.id.buscarBitacora)

        fechaReg.setOnClickListener {
            mostrarFecha()
        }
        click()
    }

    private fun mostrarFecha() {
        var fecha = Fecha({ day, month, year -> onDateSelect(day, month, year) })
        fecha.show(MainActivity.soporteF, "Fecha")
    }

    private fun onDateSelect(day: Int, month: Int, year: Int) {
        fechaReg.setText("$day/$month/$year")
    }

    fun info(): String {
        var datos: String = ""
        datos = "Fecha:${fechaReg.getText().toString()},"
                "Número Control${numContrl.text.toString()},"
                "Clave Materia${claveMa.text.toString()}"
        return datos

    }

    fun click() {
        agregarB.setOnClickListener {
        escribirArchivo("Bitacora",info())
        }
        buscarB.setOnClickListener {
            var leer:String=leerArchivo("Bitacora")
            lanzarConsulta(leer,"Bitacora")
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