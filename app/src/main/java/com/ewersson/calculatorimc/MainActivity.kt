package com.ewersson.calculatorimc

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btn_calculate: Button = findViewById(R.id.btn_calculate)
        val result: TextView = findViewById(R.id.result)

        btn_calculate.setOnClickListener {

            val height = findViewById<EditText>(R.id.height).text.toString().replace(",", ".")
                .toFloatOrNull()

            val weight = findViewById<EditText>(R.id.weight).text.toString().replace(",", ".")
                .toFloatOrNull()

            if (height == null || weight == null) {
                result.text = "Please enter valid numbers"
                result.setTextColor(Color.YELLOW)
            } else {
                val bmi = (weight * 703) / (height * height)

                when {
                    bmi < 18.5 -> {
                        result.text =
                            String.format("Your BMI is: %.2f and you are underweight", bmi)
                        result.setTextColor(Color.RED)
                    }

                    bmi in 18.5..24.9 -> {
                        result.text =
                            String.format("Your BMI is: %.2f and your weight is normal", bmi)
                        result.setTextColor(Color.GREEN)
                    }

                    bmi in 25.0..29.9 -> {
                        result.text = String.format("Your BMI is: %.2f and you are overweight", bmi)
                        result.setTextColor(Color.YELLOW)
                    }

                    bmi in 30.0..34.9 -> {
                        result.text =
                            String.format("Your BMI is: %.2f and you have grade 1 obesity", bmi)
                        result.setTextColor(Color.RED)
                    }

                    bmi in 35.0..39.9 -> {
                        result.text =
                            String.format("Your BMI is: %.2f and you have grade 2 obesity", bmi)
                        result.setTextColor(Color.RED)
                    }

                    bmi >= 40 -> {
                        result.text =
                            String.format("Your BMI is: %.2f and you have grade 3 obesity", bmi)
                        result.setTextColor(Color.RED)
                    }

                    else -> {
                        result.text = "ERROR!"
                        result.setTextColor(Color.RED)
                    }
                }
            }
        }

    }

}

