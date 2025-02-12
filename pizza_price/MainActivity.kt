package com.example.pizzapricecalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diameterInput = findViewById<EditText>(R.id.diameterInput)
        val priceInput = findViewById<EditText>(R.id.priceInput)
        val radioGroup = findViewById<RadioGroup>(R.id.shapeRadioGroup)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        calculateButton.setOnClickListener {
            val diameter = diameterInput.text.toString().toDoubleOrNull()
            val price = priceInput.text.toString().toDoubleOrNull()
            val selectedShapeId = radioGroup.checkedRadioButtonId

            if (diameter != null && price != null && selectedShapeId != -1) {
                val inchesSquared = when (selectedShapeId) {
                    R.id.radioCircle -> Math.PI * Math.pow((diameter / 2), 2.0)
                    R.id.radioRectangle -> (diameter / 2) * (diameter / 2)
                    else -> 0.0
                }

                val finalPrice = price * inchesSquared
                resultText.text = "Final Price: \$${String.format("%.2f", finalPrice)}"
            } else {
                resultText.text = "Please enter valid inputs."
            }
        }
    }
}
