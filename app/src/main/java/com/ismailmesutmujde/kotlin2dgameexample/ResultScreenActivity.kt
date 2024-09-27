package com.ismailmesutmujde.kotlin2dgameexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ismailmesutmujde.kotlin2dgameexample.databinding.ActivityResultScreenBinding

class ResultScreenActivity : AppCompatActivity() {

    private lateinit var bindingResultScreen : ActivityResultScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingResultScreen = ActivityResultScreenBinding.inflate(layoutInflater)
        val view = bindingResultScreen.root
        setContentView(view)

        bindingResultScreen.buttonTryAgain.setOnClickListener {
            val intent = Intent(this@ResultScreenActivity, MainScreenActivity::class.java)
            startActivity(intent)
        }

    }
}