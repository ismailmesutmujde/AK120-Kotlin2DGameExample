package com.ismailmesutmujde.kotlin2dgameexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ismailmesutmujde.kotlin2dgameexample.databinding.ActivityGameScreenBinding


class GameScreenActivity : AppCompatActivity() {

    private lateinit var bindingGameScreen : ActivityGameScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGameScreen = ActivityGameScreenBinding.inflate(layoutInflater)
        val view = bindingGameScreen.root
        setContentView(view)

        bindingGameScreen.mainCharacter.setOnClickListener {
            val intent = Intent(this@GameScreenActivity, ResultScreenActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}