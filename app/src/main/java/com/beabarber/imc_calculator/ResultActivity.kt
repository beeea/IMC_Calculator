package com.beabarber.imc_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class ResultActivity : AppCompatActivity() {

    private lateinit var tvResultado:TextView
    private lateinit var tvIMC:TextView
    private lateinit var tvDescripcion:TextView
    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val result:Double = intent.extras?.getDouble("IMC_RESULT") ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btnRecalculate.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when(result){
            //Bajo peso
            in 0.00..18.50 ->{
                tvResultado.text = getString(R.string.bajo_peso)
                tvResultado.setTextColor(ContextCompat.getColor(this, R.color.bajopeso))
                tvDescripcion.text = getString(R.string.def_bajo_peso)
            }
            in 18.51..24.99 ->{ //Peso normal
                tvResultado.text = getString(R.string.peso_normal)
                tvResultado.setTextColor(ContextCompat.getColor(this, R.color.pesonormal))
                tvDescripcion.text = getString(R.string.def_peso_normal)
            }
            in 25.00..29.99 ->{ //Sobrepeso
                tvResultado.text = getString(R.string.sobrepeso)
                tvResultado.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
                tvDescripcion.text = getString(R.string.def_sobrepeso)
            }
            in 30.00..99.00 ->{ //Obesidad
                tvResultado.text = getString(R.string.obesidad)
                tvResultado.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescripcion.text = getString(R.string.def_obesidad)
            }
            else -> { // Error
                tvIMC.text = getString(R.string.error)
                tvResultado.text = getString(R.string.error)
                tvResultado.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                tvDescripcion.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        tvIMC = findViewById(R.id.tvIMC)
        tvResultado = findViewById(R.id.tvResultado)
        tvDescripcion = findViewById(R.id.tvDescripcion)
        btnRecalculate = findViewById(R.id.btnRecalcular)
    }
}