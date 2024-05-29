package com.aurora.aurora.UI.Activity.VideoActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.API_Repotitory.YoutubeAPI_Repository
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.Model.Respone.YouTubeVideoListResponse
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.MainActivity.MainActivity
import com.aurora.aurora.databinding.ActivityVideoBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class VideoActivity : BaseActivity() {

    private lateinit var binding: ActivityVideoBinding
    private lateinit var videoAdapter: VideoAdapter

    @Inject
    lateinit var youtubeapiRepository: YoutubeAPI_Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVideoBinding.inflate(layoutInflater)
        videoAdapter = VideoAdapter(Constant.getListVideos(), this@VideoActivity, youtubeapiRepository)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setListVideoAdapter()
        backToMain()
    }
    private fun backToMain() {
        binding.ctToolbar.onStartIconClick = {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setListVideoAdapter() {
        binding.rvVideos.adapter = videoAdapter
        videoAdapter.onItemClickListener = {
            val intent = Intent(this@VideoActivity, VideoActivity_Screen2::class.java)
            intent.putExtra("VIDEO_ID", it.id)
            startActivity(intent)
        }

    }







}