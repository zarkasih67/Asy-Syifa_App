package com.mobile.asy_syifa.ui.materi

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.mobile.asy_syifa.databinding.ActivityVideoBinding
import com.mobile.asy_syifa.ui.soal.SoalActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo

class VideoActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_VIDEO_ID = "EXTRA_VIDEO_ID"
    }

    private lateinit var binding: ActivityVideoBinding
    private lateinit var ytPlayer: YouTubePlayer
    private var isFullScreen = false

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if (isFullScreen) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            } else {
                finishAfterTransition()
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(onBackPressedCallback)

        binding.ivMateriVideoBack?.setOnClickListener {
            onBackPressed()
        }

        binding.ivMateriVideoBack2?.setOnClickListener {
            onBackPressed()
        }

        binding.btnLatihan?.setOnClickListener {
            val intent = Intent(this, SoalActivity::class.java)
            startActivity(intent)
        }

        val ytPlayerView = binding.ytView
        val fullScreenContainer = binding.FullScreenContainer
        lifecycle.addObserver(ytPlayerView)

        ytPlayerView.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                isFullScreen = true
                fullScreenContainer.visibility = View.VISIBLE
                fullScreenContainer.addView(fullscreenView)

                WindowInsetsControllerCompat(window!!, binding.root).apply {
                    hide(WindowInsetsCompat.Type.systemBars())
                    systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
                }

                if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                }
            }

            override fun onExitFullscreen() {
                isFullScreen = false
                fullScreenContainer.visibility = View.GONE
                fullScreenContainer.removeAllViews()

                WindowInsetsControllerCompat(window!!, binding.root).apply {
                    show(WindowInsetsCompat.Type.systemBars())
                }

                if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_SENSOR) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                }
            }

        })

        val ytPlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@VideoActivity.ytPlayer = youTubePlayer

                val videoId = intent.getStringExtra(EXTRA_VIDEO_ID)
                if (!videoId.isNullOrEmpty()) {
                    youTubePlayer.loadOrCueVideo(lifecycle, videoId, 0f)
                }
            }
        }

        val iFramePlayerOptions = IFramePlayerOptions.Builder()
            .controls(1)
            .fullscreen(1)
            .build()

        ytPlayerView.enableAutomaticInitialization = false
        ytPlayerView.initialize(ytPlayerListener,iFramePlayerOptions)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (!isFullScreen) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (isFullScreen) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
            }
        }
    }
}