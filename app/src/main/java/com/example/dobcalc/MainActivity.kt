package com.example.dobcalc

import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate:TextView?=null
    private var tvAgeInMinutes:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val datepickerbtn: Button =findViewById(R.id.button)
        tvSelectedDate=findViewById(R.id.textView)
        tvAgeInMinutes=findViewById(R.id.textView3)
        datepickerbtn.setOnClickListener {
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
        val myCalender=Calendar.getInstance()
        val year=myCalender.get(Calendar.YEAR)
        val month=myCalender.get(Calendar.MONTH)
        val day=myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(this,{view,selectedyear,selectedmonth,selecteddayOfMonth->
            Toast.makeText(this,"year was $selectedyear, month was ${selectedmonth+1}, day was $selecteddayOfMonth",Toast.LENGTH_LONG).show()
            val selectedDate="$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
            tvSelectedDate?.text = selectedDate
            val sdf=SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date=sdf.parse(selectedDate)
            val selectedTime=date.time/60000
            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes=currentDate.time/60000
            val difference=currentDateInMinutes-selectedTime
            tvAgeInMinutes?.text= difference.toString()
        }, year, month, day)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()
    }
}


