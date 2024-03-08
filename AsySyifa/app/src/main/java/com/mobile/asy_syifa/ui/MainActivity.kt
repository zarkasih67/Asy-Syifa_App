package com.mobile.asy_syifa.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.mobile.asy_syifa.R
import com.mobile.asy_syifa.ui.about.AboutActivity
import com.mobile.asy_syifa.ui.materi.MateriActivity
import com.mobile.asy_syifa.ui.soal.SoalActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ivMateri = findViewById<ImageView>(R.id.iv_sejarah)
        val ivSoal = findViewById<ImageView>(R.id.iv_soal)
        val ivTentang = findViewById<ImageView>(R.id.iv_tentang)

        ivMateri.setOnClickListener {
            val intent = Intent(this, MateriActivity::class.java)
            ivMateri.context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(ivMateri.context as Activity).toBundle())

        }

        ivSoal.setOnClickListener {
            val intent = Intent(this, SoalActivity::class.java)
            ivSoal.context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(ivSoal.context as Activity).toBundle())
        }

        ivTentang.setOnClickListener {
            val intent = Intent(this, AboutActivity::class.java)
            ivTentang.context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(ivTentang.context as Activity).toBundle())
        }
    }
}