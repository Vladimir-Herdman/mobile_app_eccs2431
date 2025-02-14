package com.zybooks.pizzapricecalc

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diameterInputA = findViewById<EditText>(R.id.diameterInputA)
        val priceInputA = findViewById<EditText>(R.id.priceInputA)
        val recInputA = findViewById<EditText>(R.id.recInputA)
        val radioGroupA = findViewById<RadioGroup>(R.id.shapeRadioGroupA)

        val diameterInputB = findViewById<EditText>(R.id.diameterInputB)
        val priceInputB = findViewById<EditText>(R.id.priceInputB)
        val recInputB = findViewById<EditText>(R.id.recInputB)
        val radioGroupB = findViewById<RadioGroup>(R.id.shapeRadioGroupB)

        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextA = findViewById<TextView>(R.id.resultTextA)
        val resultTextB = findViewById<TextView>(R.id.resultTextB)
        val finalChoice = findViewById<TextView>(R.id.finalChoice)

        radioGroupA.setOnCheckedChangeListener {_, selectedShapeIdA, ->
            when (selectedShapeIdA) {
                R.id.radioCircleA -> recInputA.visibility = View.GONE
                R.id.radioRectangleA -> recInputA.visibility = View.VISIBLE
            }
        }

        radioGroupB.setOnCheckedChangeListener {_, selectedShapeIdB, ->
            when (selectedShapeIdB) {
                R.id.radioCircleB -> recInputB.visibility = View.GONE
                R.id.radioRectangleB -> recInputB.visibility = View.VISIBLE
            }
        }

        calculateButton.setOnClickListener {
            val diameterA = diameterInputA.text.toString().toDoubleOrNull()
            val widthA = recInputA.text.toString().toDoubleOrNull()
            val priceA = priceInputA.text.toString().toDoubleOrNull()
            val selectedShapeIdA = radioGroupA.checkedRadioButtonId
            var sizeA = 1.0

            var finalPriceA: Double = 1.0
            var finalPriceB: Double = 1.0

            if (diameterA != null && priceA != null && selectedShapeIdA != -1) {
                val inchesSquaredA = when (selectedShapeIdA) {
                    R.id.radioCircleA -> Math.PI * Math.pow((diameterA / 2), 2.0)
                    R.id.radioRectangleA -> diameterA * (widthA ?: 1.0)
                    else -> 0.0
                }

                finalPriceA = priceA * inchesSquaredA
                sizeA = inchesSquaredA
                resultTextA.text = "Final Price A: \$${String.format("%.2f", finalPriceA)}"
            } else {
                resultTextA.text = "Please enter valid inputs."
            }

            val diameterB = diameterInputB.text.toString().toDoubleOrNull()
            val widthB = recInputB.text.toString().toDoubleOrNull()
            val priceB = priceInputB.text.toString().toDoubleOrNull()
            val selectedShapeIdB = radioGroupB.checkedRadioButtonId
            var sizeB = 1.0

            if (diameterB != null && priceB != null && selectedShapeIdB != -1) {
                val inchesSquaredB = when (selectedShapeIdB) {
                    R.id.radioCircleB -> Math.PI * Math.pow((diameterB / 2), 2.0)
                    R.id.radioRectangleB -> diameterB * (widthB ?: 1.0)
                    else -> 0.0
                }

                finalPriceB = priceB * inchesSquaredB
                sizeB = inchesSquaredB
                resultTextB.text = "Final Price B: \$${String.format("%.2f", finalPriceB)}"
            } else {
                resultTextB.text = "Please enter valid inputs."
            }

            // Set which pizza is cheaper as final choice
            if (finalPriceA > finalPriceB) {
                finalChoice.text = "Choose A!"
            } else if (finalPriceB > finalPriceA) {
                finalChoice.text = "Choose B!"
            } else if (finalPriceA == finalPriceB) {
                if (sizeA > sizeB) {
                    finalChoice.text = "Choose A!"
                } else if (sizeB > sizeA) {
                    finalChoice.text = "Choose B!"
                } else {
                    finalChoice.text = "Choose either!"
                }
            } else {
                finalChoice.text = "You did something wrong, figure it out.  This ain't on us chief"
            }
        }
    }
}
