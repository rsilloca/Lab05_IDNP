package com.epis.lab05_idnp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnExercise2 = findViewById<Button>(R.id.go_exercise_2)
        btnExercise2.setOnClickListener {
            val intent = Intent(this, BarChartActivity::class.java)
            startActivity(intent)
        }

    }
}