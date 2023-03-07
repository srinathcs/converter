package com.example.hourseconverter

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.hourseconverter.databinding.ActivityMainBinding
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        onSetup()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onSetup() {
        binding.btnConvert.setOnClickListener {
            //onConverter()
            onConverterSimple()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onConverter() {
        val input: String = binding.etTime.text.toString()
        if (input.isNotBlank()) {
            binding.tilHours.error = null
            if (input.toInt() < 12) {
                binding.tvResult.text = "$input AM"
            } else {
                binding.tvResult.text = "$input PM"
                val formal = input.toInt() - 12
                binding.tvResult.text = "$formal PM"
            }
        } else {
            binding.tilHours.error = "Enter a Time"
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun onConverterSimple() {
        val inputTime: String = binding.etTime.text.toString()
        val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault()) // input format is 24-hour
        val outputFormat = SimpleDateFormat("h:mm a", Locale.getDefault()) // output format is 12-hour


        try {
            val date = inputFormat.parse(inputTime)
            val outputTime = date?.let { outputFormat.format(it) } // output time in 12-hour format
            binding.tvResult.text = outputTime?.uppercase()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
}