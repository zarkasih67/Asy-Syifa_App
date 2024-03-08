package com.mobile.asy_syifa.ui.soal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.mobile.asy_syifa.databinding.ActivitySoalBinding

class SoalActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySoalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSiap.setOnClickListener {
            val intent = Intent(this, DetailSoalActivity::class.java)
            binding.btnSiap.context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())

        }

        binding.ivSoalBack.setOnClickListener {
            onBackPressed()
        }
    }
}