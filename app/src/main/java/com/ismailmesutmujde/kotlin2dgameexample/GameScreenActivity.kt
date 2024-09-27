package com.ismailmesutmujde.kotlin2dgameexample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ismailmesutmujde.kotlin2dgameexample.databinding.ActivityGameScreenBinding
import kotlin.concurrent.schedule


class GameScreenActivity : AppCompatActivity() {

    private lateinit var bindingGameScreen : ActivityGameScreenBinding
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGameScreen = ActivityGameScreenBinding.inflate(layoutInflater)
        val view = bindingGameScreen.root
        setContentView(view)

        Log.e("Height1", (bindingGameScreen.cl.height).toString())
        Log.e("Width1",(bindingGameScreen.cl.width).toString())

        bindingGameScreen.cl.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                Log.e("Height2", (bindingGameScreen.cl.height).toString())
                Log.e("Width2",(bindingGameScreen.cl.width).toString())

                if(event?.action == MotionEvent.ACTION_DOWN){
                    Log.e("MotionEvent", "ACTION_DOWN : Touched the Screen")
                }
                if(event?.action == MotionEvent.ACTION_UP) {
                    Log.e("MotionEvent", "ACTION_UP : Left the Screen")
                }

                return true
            }
        })

    }
}