package com.example.appbodymassindexcalculator_xml

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        val ResultText = findViewById<TextView>(R.id.ResultText)
        val name: String = intent.extras?.getString("EXTRA NAME").orEmpty()
        ResultText.text = "Hola $name"


    }
}