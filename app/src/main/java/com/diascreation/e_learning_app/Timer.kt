package com.diascreation.e_learning_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Timer : AppCompatActivity() {
    private lateinit var timerTextView: TextView
    private lateinit var playButton: ImageButton
    private lateinit var pauseButton: ImageButton
    private lateinit var skipButton: ImageButton

    private var countDownTimer: CountDownTimer? = null
    private var timeInMillis: Long = 1500000 // 25 minutes in milliseconds
    private var isRunning = false

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timerTextView = findViewById(R.id.timerTextView)
        playButton = findViewById(R.id.playButton)
        pauseButton = findViewById(R.id.pauseButton)
        skipButton = findViewById(R.id.skipButton)

        updateTimerText()

        playButton.setOnClickListener {
            if (!isRunning) {
                startTimer()
            }
        }

        pauseButton.setOnClickListener {
            if (isRunning) {
                pauseTimer()
            }
        }

        skipButton.setOnClickListener {
            skipTimer()
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeInMillis = millisUntilFinished
                updateTimerText()
            }

            override fun onFinish() {
                isRunning = false
                updateTimerText()
            }
        }.start()
        isRunning = true
    }

    private fun pauseTimer() {
        countDownTimer?.cancel()
        isRunning = false
    }

    private fun skipTimer() {
        pauseTimer()
        timeInMillis = 1500000 // Reset to 25 minutes
        updateTimerText()
    }

    private fun updateTimerText() {
        val minutes = (timeInMillis / 1000) / 60
        val seconds = (timeInMillis / 1000) % 60
        val timeFormatted = String.format("%02d:%02d", minutes, seconds)
        timerTextView.text = timeFormatted
    }
}