package com.example.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner

class Menu : Fragment() {

    lateinit var estudiantes: ImageView
    lateinit var salas: ImageView
    lateinit var bitacora: ImageView
    lateinit var asignatura: ImageView
    val estudianteFragment: Estudiantes = Estudiantes()
    val salaFragment: SalaComputo = SalaComputo()
    val asignaturaFragment: Asignatura = Asignatura()
    val bitacoraFragment: Bitacora = Bitacora()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_menu, container, false)
        inicializar(vista)
        return vista
    }

    fun inicializar(view: View) {

        estudiantes = view.findViewById(R.id.icoEstudiante)
        salas = view.findViewById(R.id.icoSala)
        bitacora = view.findViewById(R.id.icoBitacora)
        asignatura = view.findViewById(R.id.icoAsignatura)
        click()
    }

    fun click() {
        estudiantes.setOnClickListener {
            MainActivity.soporteF.beginTransaction().replace(R.id.container, estudianteFragment).commit()
        }
        salas.setOnClickListener {
            MainActivity.soporteF.beginTransaction().replace(R.id.container, salaFragment).commit()
        }
        bitacora.setOnClickListener {
            MainActivity.soporteF.beginTransaction().replace(R.id.container, bitacoraFragment).commit()
        }
        asignatura.setOnClickListener {
            MainActivity.soporteF.beginTransaction().replace(R.id.container, asignaturaFragment).commit()
        }
    }
}