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

    private var score= 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingGameScreen = ActivityGameScreenBinding.inflate(layoutInflater)
        val view = bindingGameScreen.root
        setContentView(view)

        //Move objects off the screen
        bindingGameScreen.blackBox.x = -8000.0f
        bindingGameScreen.blackBox.y = -8000.0f
        bindingGameScreen.yellowCircle.x = -8000.0f
        bindingGameScreen.yellowCircle.y = -8000.0f
        bindingGameScreen.redTriangle.x = -8000.0f
        bindingGameScreen.redTriangle.y = -8000.0f

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

                    //Makes the user startup warning invisible.
                    bindingGameScreen.textViewStartGame.visibility = View.INVISIBLE

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
                            collisionControl()
                        }
                    }
                }

                return true
            }
        })

    }

    fun mainCharacterMovement() {

        val mainCharacterVelocity = screenHeight/60.0f     //1280 / 60.0f  = 20.0f

        if(touchControl){
            mainCharacterY -= mainCharacterVelocity
        }else{
            mainCharacterY += mainCharacterVelocity
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

    fun collisionControl() {

        val yellowCircleCenterX = yellowCircleX + bindingGameScreen.yellowCircle.width / 2.0f
        val yellowCircleCenterY = yellowCircleY + bindingGameScreen.yellowCircle.height / 2.0f

        if (0.0f <= yellowCircleCenterX && yellowCircleCenterX <= mainCharacterWidth
            && mainCharacterY <= yellowCircleCenterY && yellowCircleCenterY <= mainCharacterY + mainCharacterHeight
        ) {
            score += 20
            yellowCircleX = -10.0f
        }

        val redTriangleCenterX = redTriangleX + bindingGameScreen.redTriangle.width / 2.0f
        val redTriangleCenterY = redTriangleY + bindingGameScreen.redTriangle.height / 2.0f

        if (0.0f <= redTriangleCenterX && redTriangleCenterX <= mainCharacterWidth
            && mainCharacterY <= redTriangleCenterY && redTriangleCenterY <= mainCharacterY + mainCharacterHeight
        ) {
            score += 50
            redTriangleX = -10.0f
        }

        val blackBoxXCenterX = blackBoxX + bindingGameScreen.blackBox.width / 2.0f
        val blackBoxXCenterY = blackBoxY + bindingGameScreen.blackBox.height / 2.0f

        if (0.0f <= blackBoxXCenterX && blackBoxXCenterX <= mainCharacterWidth
            && mainCharacterY <= blackBoxXCenterY && blackBoxXCenterY <= mainCharacterY + mainCharacterHeight
        ) {
            blackBoxX = -10.0f

            timer.cancel() // Stop timer

            val intent = Intent(this@GameScreenActivity, ResultScreenActivity::class.java)
            intent.putExtra("score", score)
            startActivity(intent)
        }
        bindingGameScreen.textViewScore.text = score.toString()

    }
}