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

    private lateinit var result: TextView
    private lateinit var weightUnitSpinner: Spinner
    private lateinit var heightUnitSpinner: Spinner

    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupEdgeToEdge()
        initializeViews()
        setupSpinners()
        setupCalculateButton()
    }

    override fun onStart() {
        super.onStart()
        showLoadingScreen()
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    private fun setupEdgeToEdge() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initializeViews() {
        result = findViewById(R.id.result)
        weightUnitSpinner = findViewById(R.id.weight_unit)
        heightUnitSpinner = findViewById(R.id.height_unit)
    }

    private fun setupSpinners() {
        val weightUnits = arrayOf("kg", "lb")
        val heightUnits = arrayOf("cm", "inches")
        weightUnitSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, weightUnits)
        heightUnitSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, heightUnits)
    }

    private fun setupCalculateButton() {
        val btnCalculate: Button = findViewById(R.id.btn_calculate)
        btnCalculate.setOnClickListener { calculateBMI() }
    }

    @SuppressLint("DefaultLocale")
    private fun calculateBMI() {
        val height = findViewById<EditText>(R.id.height).text.toString().replace(",", ".").toFloatOrNull()
        val weight = findViewById<EditText>(R.id.weight).text.toString().replace(",", ".").toFloatOrNull()
        val selectedWeightUnit = weightUnitSpinner.selectedItem.toString()
        val selectedHeightUnit = heightUnitSpinner.selectedItem.toString()

        if (height == null || weight == null) {
            showError("Please enter valid numbers")
        } else {
            val weightInKg = if (selectedWeightUnit == "lb") weight * 0.453592f else weight
            val heightInMeters = if (selectedHeightUnit == "cm") height / 100f else height * 0.0254f

            if (heightInMeters > 0) {
                val bmi = weightInKg / (heightInMeters * heightInMeters)
                showBMIResult(bmi)
            } else {
                showError("Invalid input. Please enter valid numbers.")
            }
        }
    }

    private fun showBMIResult(bmi: Float) {
        when {
            bmi < 18.5 -> {
                updateResult("Your BMI is: %.2f and you are underweight".format(bmi), Color.RED)
            }
            bmi in 18.5..24.9 -> {
                updateResult("Your BMI is: %.2f and your weight is normal".format(bmi), Color.GREEN)
            }
            bmi in 25.0..29.9 -> {
                updateResult("Your BMI is: %.2f and you are overweight".format(bmi), Color.YELLOW)
            }
            bmi >= 30 -> {
                updateResult("Your BMI is: %.2f and you are obese".format(bmi), Color.RED)
            }
        }
    }

    private fun updateResult(message: String, color: Int) {
        result.text = message
        result.setTextColor(color)
    }

    private fun showError(message: String) {
        updateResult(message, Color.YELLOW)
    }

    private fun showLoadingScreen() {
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show()
    }

    private fun updateUI() {
        Toast.makeText(this, "Welcome back!", Toast.LENGTH_SHORT).show()
    }


}
