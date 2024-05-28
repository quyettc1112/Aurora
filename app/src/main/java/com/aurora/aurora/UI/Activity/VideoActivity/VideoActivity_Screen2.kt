package com.aurora.aurora.UI.Activity.VideoActivity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.R
import com.aurora.aurora.databinding.ActivityVideoScreen2Binding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.FullscreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoActivity_Screen2 : BaseActivity() {

    private lateinit var binding: ActivityVideoScreen2Binding
    private lateinit var youtubePlayer: YouTubePlayer
    private var isFullScreen = false
    private lateinit var videoIDItent: String

    // Cấu hình OnBackPressed
    private val onBackPressedCallback = object: OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            if(isFullScreen) {
                youtubePlayer.toggleFullscreen()
            } else {
                finish()
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVideoScreen2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        onBackPressedDispatcher.addCallback(onBackPressedCallback)
        lifecycle.addObserver(binding.wvVideoIframes)
        backToVideoActityScreen1()

        videoIDItent = intent.getStringExtra("VIDEO_ID") ?: ""

        binding.wvVideoIframes.addFullscreenListener(object : FullscreenListener {
            override fun onEnterFullscreen(fullscreenView: View, exitFullscreen: () -> Unit) {
                isFullScreen = true
                binding.flFullScrenContainer.visibility = View.VISIBLE
                binding.flFullScrenContainer.addView(fullscreenView)

                // Remove Screen status
                WindowInsetsControllerCompat(window!!, findViewById(R.id.youtubePlayerView)).apply {
                    hide(WindowInsetsCompat.Type.systemBars())
                    systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }

                if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
                }
            }
            override fun onExitFullscreen() {
                isFullScreen = false
                binding.flFullScrenContainer.let {
                    it.visibility = View.GONE
                    it.removeAllViews()
                }

                // Remove Screen status
                WindowInsetsControllerCompat(window!!, findViewById(R.id.youtubePlayerView)).apply {
                    show(WindowInsetsCompat.Type.systemBars())
                }

                if (requestedOrientation != ActivityInfo.SCREEN_ORIENTATION_SENSOR) {
                    requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT
                }

            }

        })

        val youtubeListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                this@VideoActivity_Screen2.youtubePlayer = youTubePlayer
                val videoId = videoIDItent
                youTubePlayer.loadOrCueVideo(lifecycle,videoId, 0f)
            }
        }
        val iFramePlayerOption = IFramePlayerOptions.Builder()
            .controls(1)
            .fullscreen(1)
            .build()

        binding.wvVideoIframes.enableAutomaticInitialization = false
        binding.wvVideoIframes.initialize(youtubeListener, iFramePlayerOption)

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (!isFullScreen) {
                youtubePlayer.toggleFullscreen()
            }
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (isFullScreen) {
                youtubePlayer.toggleFullscreen()
            }
        }
    }

    private fun backToVideoActityScreen1() {
        binding.ctToolbarVideoActivity.onStartIconClick = {
            startActivity(Intent(this@VideoActivity_Screen2, VideoActivity::class.java))
        }

    }




}