package com.beabarber.imc_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result:Double = intent.extras?.getDouble("IMC_RESULT") ?: -1.0

    }
}