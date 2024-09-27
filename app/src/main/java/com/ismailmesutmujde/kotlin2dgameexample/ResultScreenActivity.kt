package com.ismailmesutmujde.kotlin2dgameexample

import android.content.Context
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

        val score = intent.getIntExtra("score",0)
        bindingResultScreen.textViewTotalScore.text = score.toString()

        val sp = getSharedPreferences("Result", Context.MODE_PRIVATE)
        val highestScore = sp.getInt("highestScore", 0)

        if (score > highestScore){

            val editor = sp.edit()
            editor.putInt("highestScore", score)
            editor.commit()

            bindingResultScreen.textViewHighestScore.text = score.toString()
        }else{
            bindingResultScreen.textViewHighestScore.text = highestScore.toString()
        }

        bindingResultScreen.buttonTryAgain.setOnClickListener {
            val intent = Intent(this@ResultScreenActivity, MainScreenActivity::class.java)
            startActivity(intent)
        }

    }
}