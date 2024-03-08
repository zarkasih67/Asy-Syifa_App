package com.mobile.asy_syifa.ui

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mobile.asy_syifa.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val SPLASH_TIME_OUT: Long = 3500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        playAnimation()

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)

    }

    private fun playAnimation() {
        val circle1 = ObjectAnimator.ofFloat(binding.imageView, View.ALPHA, 1f).setDuration(1000)
        val circle2 = ObjectAnimator.ofFloat(binding.imageView6, View.ALPHA, 1f).setDuration(1000)
        val circle3 = ObjectAnimator.ofFloat(binding.imageView7, View.ALPHA, 1f).setDuration(1000)
        val logo = ObjectAnimator.ofFloat(binding.imageView2, View.ALPHA, 1f).setDuration(1000)
        val presented = ObjectAnimator.ofFloat(binding.textView, View.ALPHA, 1f).setDuration(1000)
        val ti = ObjectAnimator.ofFloat(binding.imageView8, View.ALPHA, 1f).setDuration(1000)
        val thibbun = ObjectAnimator.ofFloat(binding.imageView9, View.ALPHA, 1f).setDuration(1000)

        val circleTogether = AnimatorSet().apply {
            playTogether(circle1, circle3)
        }

        val together = AnimatorSet().apply {
            playTogether(ti, thibbun)
        }

        AnimatorSet().apply {
            playSequentially(circleTogether, circle2, logo, presented, together)
            start()
        }
    }
}