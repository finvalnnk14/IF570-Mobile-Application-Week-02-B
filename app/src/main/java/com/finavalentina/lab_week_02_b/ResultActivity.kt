package com.finavalentina.lab_week_02_b

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"
    }

    private val backButton: Button
        get() = findViewById(R.id.back_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        if(intent != null){
            val colorCode = intent.getStringExtra(COLOR_KEY)
            val backgroundScreen =
                findViewById<ConstraintLayout>(R.id.background_screen)
            try {
                backgroundScreen.setBackgroundColor(Color.parseColor("#$colorCode"))
            }
            catch (ex: IllegalArgumentException){
                Intent().let{
                        errorIntent ->
                    errorIntent.putExtra(ERROR_KEY, true)
                    setResult(Activity.RESULT_OK, errorIntent)
                    finish()
                } }
            val resultMessage =
                findViewById<TextView>(R.id.color_code_result_message)
            resultMessage.text = getString(R.string.color_code_result_message,
                colorCode?.uppercase())

            backButton.setOnClickListener {
                finish()
            }
        }
    }
}