package com.beabarber.imc_calculator

import android.content.Intent
import android.health.connect.datatypes.WeightRecord
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class IMCActivity : AppCompatActivity() {

    private lateinit var viewFemale:CardView
    private lateinit var viewMale:CardView
    private lateinit var textviewHeight:TextView
    private lateinit var slider:RangeSlider
    private lateinit var btnSubstractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnCalcular:Button

    private var isFemaleSelected:Boolean = true
    private var isMaleSelected:Boolean = true
    private var currentWeight:Int = 60
    private var currentHeight:Int = 150

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewFemale = findViewById(R.id.viewFemale)
        viewMale = findViewById(R.id.viewMale)
        textviewHeight = findViewById(R.id.textviewHeight)
        slider = findViewById(R.id.slider)
        btnSubstractWeight = findViewById(R.id.btnSubstractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnCalcular = findViewById(R.id.btnCalcular)
    }

    private fun initListeners() {
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor() }
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor() }
        slider.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            textviewHeight.text = "$currentHeight cm"
        }
        btnPlusWeight.setOnClickListener{
            currentWeight++
            setWeight()
        }
        btnSubstractWeight.setOnClickListener{
            currentWeight--
            setWeight()
        }
        btnCalcular.setOnClickListener {
            val result = calculateIMC()
            goToResult(result)
        }
    }

    private fun goToResult(result: Double) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("IMC_RESULT", result)
        startActivity(intent)
    }

    private fun calculateIMC():Double {
        val df = DecimalFormat("#.##")
        val heightInDecimal = currentHeight.toDouble()/100
        val imc = currentWeight/(heightInDecimal*heightInDecimal)
        return df.format(imc).toDouble()
    }

    private fun setWeight(){
        tvWeight.text = currentWeight.toString()
    }
    private fun changeGender(){
        isFemaleSelected = !isMaleSelected
        isMaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean):Int{
        val colorReference = if(isSelectedComponent){
            R.color.background_cardview_selected
        }else{
            R.color.cardviews
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
    }
}