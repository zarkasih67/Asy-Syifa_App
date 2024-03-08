package com.mobile.asy_syifa.ui.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mobile.asy_syifa.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivAboutBack.setOnClickListener {
            onBackPressed()
        }
    }
}