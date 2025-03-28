package com.example.evidencia2_skillmatch

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var backgroundImage: ImageView
    private val images = listOf(R.drawable.fondo1, R.drawable.fondo2)
    private var currentIndex = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        backgroundImage = findViewById(R.id.backgroundImage)
        startImageRotation()
    }

    private fun startImageRotation() {
        val imageSwitcher = object : Runnable {
            override fun run() {
                backgroundImage.setImageResource(images[currentIndex])
                currentIndex = (currentIndex + 1) % images.size
                handler.postDelayed(this, 20000) // 20 segundos
            }
        }
        handler.post(imageSwitcher)
    }
}
