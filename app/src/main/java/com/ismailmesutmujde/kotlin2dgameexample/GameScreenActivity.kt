package com.ismailmesutmujde.kotlin2dgameexample

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ismailmesutmujde.kotlin2dgameexample.databinding.ActivityGameScreenBinding
import java.util.Timer
import kotlin.concurrent.schedule
import kotlin.math.floor


class GameScreenActivity : AppCompatActivity() {

    private lateinit var bindingGameScreen : ActivityGameScreenBinding

    //Positions
    private var mainCharacterX = 0.0f
    private var mainCharacterY = 0.0f
    private var blackBoxX = 0.0f
    private var blackBoxY = 0.0f
    private var yellowCircleX = 0.0f
    private var yellowCircleY = 0.0f
    private var redTriangleX = 0.0f
    private var redTriangleY = 0.0f


    //Dimensions
    private var screenWidth = 0
    private var screenHeight = 0
    private var mainCharacterWidth = 0
    private var mainCharacterHeight = 0

    //Controls
    private var touchControl = false
    private var startControl = false

    private val timer = Timer()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGameScreen = ActivityGameScreenBinding.inflate(layoutInflater)
        val view = bindingGameScreen.root
        setContentView(view)

        //Log.e("Height1", (bindingGameScreen.cl.height).toString())
        //Log.e("Width1",(bindingGameScreen.cl.width).toString())

        bindingGameScreen.cl.setOnTouchListener(object: View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {

                //Log.e("Height2", (bindingGameScreen.cl.height).toString())
                //Log.e("Width2",(bindingGameScreen.cl.width).toString())

                if(startControl){
                    if(event?.action == MotionEvent.ACTION_DOWN){
                        Log.e("MotionEvent", "ACTION_DOWN : Touched the Screen")
                        touchControl = true
                    }
                    if(event?.action == MotionEvent.ACTION_UP) {
                        Log.e("MotionEvent", "ACTION_UP : Left the Screen")
                        touchControl = false
                    }
                }else{
                    startControl = true

                    // Main Character's x and y position is taken according to the starting position on the screen
                    mainCharacterX = bindingGameScreen.mainCharacter.x
                    mainCharacterY = bindingGameScreen.mainCharacter.y

                    mainCharacterWidth = bindingGameScreen.mainCharacter.width
                    mainCharacterHeight = bindingGameScreen.mainCharacter.height
                    screenWidth = bindingGameScreen.cl.width
                    screenHeight = bindingGameScreen.cl.height

                    timer.schedule(0,20) {
                        Handler(Looper.getMainLooper()).post {
                            mainCharacterMovement()
                            movingObjects()
                        }
                    }
                }

                return true
            }
        })

    }

    fun mainCharacterMovement() {

        if(touchControl){
            mainCharacterY -=20.0f
        }else{
            mainCharacterY +=20.0f
        }
        bindingGameScreen.mainCharacter.y = mainCharacterY

        if(mainCharacterY <= 0.0f){
            mainCharacterY = 0.0f
        }

        if(mainCharacterY >= screenHeight - mainCharacterHeight){
            mainCharacterY = (screenHeight - mainCharacterHeight).toFloat()
        }

        bindingGameScreen.mainCharacter.y = mainCharacterY
    }

    fun movingObjects() {

        blackBoxX -= screenWidth/44.0f     //1080 / 44.0f  = 25.0f
        yellowCircleX -= screenWidth/54.0f //1080 / 54.0f  = 20.0f
        redTriangleX -= screenWidth/36.0f  //1080 / 36.0f  = 30.0f

        if (blackBoxX < 0.0f ){
            blackBoxX = screenWidth + 20.0f
            blackBoxY = floor(Math.random() * screenHeight).toFloat()
        }
        bindingGameScreen.blackBox.x = blackBoxX
        bindingGameScreen.blackBox.y = blackBoxY

        if (yellowCircleX < 0.0f ){
            yellowCircleX = screenWidth + 20.0f
            yellowCircleY = floor(Math.random() * screenHeight).toFloat()
        }
        bindingGameScreen.yellowCircle.x = yellowCircleX
        bindingGameScreen.yellowCircle.y = yellowCircleY

        if (redTriangleX < 0.0f ){
            redTriangleX = screenWidth + 20.0f
            redTriangleY = floor(Math.random() * screenHeight).toFloat()
        }
        bindingGameScreen.redTriangle.x = redTriangleX
        bindingGameScreen.redTriangle.y = redTriangleY
    }
}