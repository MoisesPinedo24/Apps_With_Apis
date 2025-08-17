package com.example.appbodymassindexcalculator_xml

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import bmccalculator.BMC_Calculator_Activity
import superheroapp.SuperHeroListActivity
import todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_menu)
        val BtnSaludApp = findViewById<ImageButton>(R.id.BtnSaludApp)
        val BtnBMC_App = findViewById<ImageButton>(R.id.BtnBMC_App)
        val btnTODO = findViewById<ImageButton>(R.id.btnTODO)
        val btnSuperHero = findViewById<ImageButton>(R.id.btnSuperHero)


        BtnSaludApp.setOnClickListener { AppSaludar() }
        BtnBMC_App.setOnClickListener { AppBMC() }
        btnTODO.setOnClickListener { navigatetoTodoApp() }
        btnSuperHero.setOnClickListener { navigateSuperHeroApp() }

    }


    private fun AppSaludar() {
        nextApp(MainActivity::class.java)
    }

    private fun AppBMC() {
        nextApp(BMC_Calculator_Activity::class.java)
    }

    private fun navigatetoTodoApp() {
        nextApp(TodoActivity::class.java)
    }

    private fun navigateSuperHeroApp() {
        nextApp(SuperHeroListActivity::class.java)
    }

    private fun <T> nextApp(newActivity: Class<T>) {
        val intent = Intent(this, newActivity)
        startActivity(intent)
        Intent()
    }

}

