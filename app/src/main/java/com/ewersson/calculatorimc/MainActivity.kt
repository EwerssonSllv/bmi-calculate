package com.ewersson.calculatorimc

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnCalculate: Button = findViewById(R.id.btn_calculate)
        val result: TextView = findViewById(R.id.result)

        val weightUnitSpinner: Spinner = findViewById(R.id.weight_unit)
        val heightUnitSpinner: Spinner = findViewById(R.id.height_unit)

        val weightUnits = arrayOf("kg", "lb")
        val heightUnits = arrayOf("cm", "inches")

        weightUnitSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, weightUnits)
        heightUnitSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, heightUnits)

        btnCalculate.setOnClickListener {

            val height = findViewById<EditText>(R.id.height).text.toString().replace(",", ".")
                .toFloatOrNull()
            val weight = findViewById<EditText>(R.id.weight).text.toString().replace(",", ".")
                .toFloatOrNull()

            val selectedWeightUnit = weightUnitSpinner.selectedItem.toString()
            val selectedHeightUnit = heightUnitSpinner.selectedItem.toString()

            if (height == null || weight == null) {
                result.text = "Please enter valid numbers"
                result.setTextColor(Color.YELLOW)
            } else {

                val weightInKg: Float = if (selectedWeightUnit == "lb") weight
                    .times(0.453592f) else weight

                val heightInMeters: Float = if (selectedHeightUnit == "cm") height
                    .div(100f) else height.times(0.0254f)

                if (heightInMeters > 0) {
                    val bmi = weightInKg / (heightInMeters * heightInMeters)

                    when {
                        bmi < 18.5 -> {
                            result.text = String.format("Your BMI is: %.2f and you are underweight", bmi)
                            result.setTextColor(Color.RED)
                        }
                        bmi in 18.5..24.9 -> {
                            result.text = String.format("Your BMI is: %.2f and your weight is normal", bmi)
                            result.setTextColor(Color.GREEN)
                        }
                        bmi in 25.0..29.9 -> {
                            result.text = String.format("Your BMI is: %.2f and you are overweight", bmi)
                            result.setTextColor(Color.YELLOW)
                        }
                        bmi >= 30 -> {
                            result.text = String.format("Your BMI is: %.2f and you are obese", bmi)
                            result.setTextColor(Color.RED)
                        }
                    }
                } else {
                    result.text = "Invalid input. Please enter valid numbers."
                    result.setTextColor(Color.YELLOW)
                }


            }
            }
        }

    }
