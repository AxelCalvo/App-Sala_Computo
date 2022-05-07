package com.example.fragment

import android.content.Context
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class Estudiantes : Fragment() {

    lateinit var numContrl: EditText
    lateinit var nombreAlumno: EditText
    lateinit var semestre: Spinner
    lateinit var carrera: Spinner
    lateinit var Ra: RadioButton
    lateinit var Rb: RadioButton
    lateinit var buscarEstu: ExtendedFloatingActionButton
    lateinit var agregarEstu: ExtendedFloatingActionButton
    val ruta = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista=inflater.inflate(R.layout.fragment_estudiantes, container, false)
        inicializar(vista)
        return vista

    }
    fun inicializar(view: View){
        nombreAlumno= view.findViewById(R.id.nombreAlumno)
        numContrl= view.findViewById(R.id.numControlEstu)
        semestre= view.findViewById(R.id.semestre)
        carrera= view.findViewById(R.id.carrera)
        Ra= view.findViewById(R.id.grupoA)
        Rb= view.findViewById(R.id.grupoB)
        buscarEstu= view.findViewById(R.id.agregarEstu)
        agregarEstu= view.findViewById(R.id.buscarEstu)
        click()
    }
    fun info():String{
        var datos:String= ""
        datos+="Número de Control:${numContrl.text.toString()}," +"Nombre Alumno :${nombreAlumno.text.toString()}," +
                "Carrera:${carrera.selectedItem.toString()},"
                 "Semestre${semestre.selectedItem.toString()}," +
                         "Grupo:${if (Ra.isChecked) "A" else "B"}"
        return datos

    }
    fun click() {
        agregarEstu.setOnClickListener {
            escribirArchivo("Estudiantes",info())
        }
        buscarEstu.setOnClickListener {
            var leer:String=leerArchivo("Estudiantes")
            lanzarConsulta(leer,"Estudiantes")
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