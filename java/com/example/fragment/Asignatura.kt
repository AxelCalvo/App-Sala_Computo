package com.example.fragment

import android.content.Context
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Spinner
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter


class Asignatura : Fragment() {
    lateinit var clave: EditText
    lateinit var nombreMateria: EditText
    lateinit var dia: Spinner
    lateinit var horaEntrada: EditText
    lateinit var horaSalida: EditText
    lateinit var agregarA: ExtendedFloatingActionButton
    lateinit var buscarA: ExtendedFloatingActionButton
    val ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_asignatura, container, false)
        inicializar(vista)
        return vista
    }

    fun inicializar(view: View) {
        clave = view.findViewById(R.id.claveAsignatura)
        nombreMateria = view.findViewById(R.id.materia)
        dia = view.findViewById(R.id.diaImpartir)
        horaEntrada = view.findViewById(R.id.horaEntrada)
        horaSalida = view.findViewById(R.id.horaSalida)
        agregarA = view.findViewById(R.id.agregarAsignatura)
        buscarA = view.findViewById(R.id.buscarAsignatura)
        click()
    }

    fun info(): String {
        var datos: String = ""
        datos += "Clave:${clave.text.toString()}," +
                "Materia :${nombreMateria.text.toString()}," +
                "Dia a Impartir:${dia.selectedItem.toString()},"
        "Hora de Entrada:${horaEntrada.text.toString()}," +
                "Hora de salida:${horaSalida.text.toString()}"
        return datos

    }

    fun click() {
        agregarA.setOnClickListener {
            escribirArchivo("Asignatura",info())
        }
        buscarA.setOnClickListener {
            var leer:String=leerArchivo("Asignatura")
            lanzarConsulta(leer,"Asignatura")
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