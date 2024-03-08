package com.mobile.asy_syifa.ui.materi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import com.mobile.asy_syifa.databinding.ActivityMateriBinding

class MateriActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMateriBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMateriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivMateriBack.setOnClickListener {
            onBackPressed()
        }

        binding.ivSejarah.setOnClickListener {
            startFullScreenActivity("QO1SmAhUfWM")
        }

         binding.ivPerlengkapan.setOnClickListener {
            startFullScreenActivity("7jh_wGhVX-o")
        }

        binding.ivTitkBekam.setOnClickListener {
            startFullScreenActivity("DlPGA6wzVoI")
        }

        binding.ivLarangan.setOnClickListener {
            startFullScreenActivity("dW_UcooIYEU")
        }
    }

    private fun startFullScreenActivity(videoId: String) {
        val intent = Intent(this, VideoActivity::class.java)
        intent.putExtra(VideoActivity.EXTRA_VIDEO_ID, videoId)
        startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this).toBundle())
    }
}