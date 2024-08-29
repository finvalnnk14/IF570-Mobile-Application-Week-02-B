package com.finavalentina.lab_week_02_b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.finavalentina.lab_week_02_b.ui.theme.LAB_WEEK_02_BTheme
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"
    }
    private val submitButton: Button
        get() = findViewById(R.id.submit_button)

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                activityResult ->
            val data = activityResult.data
            val error = data?.getBooleanExtra(ERROR_KEY, false)
            if(error == true){
                Toast
                    .makeText(this, getString(R.string.color_code_input_invalid),
                        Toast.LENGTH_LONG)
                    .show()
            } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        submitButton.setOnClickListener {
            val colorCode =
                findViewById<TextInputEditText>(R.id.color_code_input_field).text.toString()
            if (colorCode.isNotEmpty()) {
                if (colorCode.length < 6) {
                    Toast
                        .makeText(
                            this,
                            getString(R.string.color_code_input_wrong_length), Toast.LENGTH_LONG
                        )
                        .show()
                } else {
                    val ResultIntent = Intent(this, ResultActivity::class.java)
                    ResultIntent.putExtra(COLOR_KEY, colorCode)
                    //startActivity(ResultIntent)
                    startForResult.launch(ResultIntent)
                }
            } else {
                Toast
                    .makeText(
                        this,
                        getString(R.string.color_code_input_empty), Toast.LENGTH_LONG
                    ).show()
            }
        }
    }
}