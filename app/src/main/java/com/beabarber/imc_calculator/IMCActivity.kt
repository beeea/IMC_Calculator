package com.beabarber.imc_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class IMCActivity : AppCompatActivity() {

    private lateinit var viewFemale:CardView
    private lateinit var viewMale:CardView
    private lateinit var textviewHeight:TextView
    private lateinit var slider:RangeSlider

    private var isFemaleSelected:Boolean = true
    private var isMaleSelected:Boolean = true

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
            val result = df.format(value)
            textviewHeight.text = "$result cm"
        }
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
    }
}