package com.example.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.*

class Fecha (val listener:(day:Int,month:Int,year:Int)->Unit): DialogFragment(),DatePickerDialog.OnDateSetListener {
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        listener(p3,p2,p1)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar:Calendar= Calendar.getInstance()
        val dia:Int=calendar.get(Calendar.DAY_OF_MONTH)
        val mes:Int=calendar.get(Calendar.MONTH)
        val anio:Int=calendar.get(Calendar.YEAR)
        val res=DatePickerDialog(activity as Context,this,anio,mes,dia)
        return res

    }
}