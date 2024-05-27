package com.aurora.aurora.UI.Activity.VideoActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

    @Inject
    lateinit var youtubeapiRepository: YoutubeAPI_Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityVideoBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }



      // callYoutubeVideoList()

        backToMain()
    }

    private fun backToMain() {
        binding.ctToolbar.onStartIconClick = {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun callYoutubeVideoList() {
        youtubeapiRepository.getVideoList(
            id = "7lCDEYXw3mM",
            apiKey = "AIzaSyCfH9LJPxphcne7DED5y7YzUvEM7TZ43UI",
            part = "snippet,contentDetails,statistics,status"
        ).enqueue(object : Callback<YouTubeVideoListResponse>{
            override fun onResponse(
                call: retrofit2.Call<YouTubeVideoListResponse>,
                response: Response<YouTubeVideoListResponse>
            ) {
                if (response.isSuccessful) {
                    val videoListResponse = response.body()
                    videoListResponse?.let {
                        Log.d("YouTubeApi", "Video List Response: $it")
                        it.items.forEach { videoItem ->
                            Log.d("YouTubeApi", "Video ID: ${videoItem.id}")
                            Log.d("YouTubeApi", "Title: ${videoItem.snippet.title}")
                            Log.d("YouTubeApi", "Description: ${videoItem.snippet.description}")
                            Log.d("YouTubeApi", "Published At: ${videoItem.snippet.publishedAt}")
                            Log.d("YouTubeApi", "Channel Title: ${videoItem.snippet.channelTitle}")
                            Log.d("YouTubeApi", "View Count: ${videoItem.statistics.viewCount}")
                            Log.d("YouTubeApi", "Like Count: ${videoItem.statistics.likeCount}")
                            Log.d("YouTubeApi", "Comment Count: ${videoItem.statistics.commentCount}")
                            // Log other details as needed
                        }
                    } ?: run {
                        Log.d("YouTubeApi", "Response body is null")
                    }
                } else {
                    Log.d("YouTubeApi", "Response failed with code: ${response.code()} and message: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<YouTubeVideoListResponse>, t: Throwable) {
                Log.d("YouTubeApi", "Failed to retrieve data: ${t.message}")
            }


        })

    }






}