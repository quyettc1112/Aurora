package com.aurora.aurora.UI.Activity.VideoActivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.MainActivity.MainActivity
import com.aurora.aurora.databinding.ActivityVideoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoActivity : BaseActivity() {

    private lateinit var binding: ActivityVideoBinding
    private lateinit var videoAdapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVideoBinding.inflate(layoutInflater)
        videoAdapter = VideoAdapter(Constant.getListVideos(), this, binding)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.rvVideos.adapter = videoAdapter


        backToMain()
    }

    private fun backToMain() {
        binding.ctToolbar.onStartIconClick = {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }






}