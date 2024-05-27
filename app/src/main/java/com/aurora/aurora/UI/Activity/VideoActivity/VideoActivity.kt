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



       callYoutubeVideoList()

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
            part = "snippet,contentDetails"
        ).enqueue(object : Callback<YouTubeVideoListResponse>{
            override fun onResponse(
                call: Call<YouTubeVideoListResponse>,
                response: Response<YouTubeVideoListResponse>
            ) {
                if (response.isSuccessful) {
                    val videoListResponse = response.body()
                    videoListResponse?.let {
                        val youTubeVideoListResponse = YouTubeVideoListResponse(
                            kind = it.kind,
                            etag = it.etag,
                            items = it.items.map { videoItem ->
                                YouTubeVideoListResponse.VideoItem(
                                    kind = videoItem.kind,
                                    etag = videoItem.etag,
                                    id = videoItem.id,
                                    snippet = YouTubeVideoListResponse.Snippet(
                                        title = videoItem.snippet.title,
                                        description = videoItem.snippet.description,
                                        thumbnails = YouTubeVideoListResponse.Thumbnails(
                                            default = videoItem.snippet.thumbnails.default,
                                            medium = videoItem.snippet.thumbnails.medium,
                                            high = videoItem.snippet.thumbnails.high
                                        ),
                                    )
                                )
                            },
                            pageInfo = YouTubeVideoListResponse.PageInfo(
                                totalResults = it.pageInfo.totalResults,
                                resultsPerPage = it.pageInfo.resultsPerPage
                            )
                        )
                        val firstVideoId = youTubeVideoListResponse.items.firstOrNull()
                        firstVideoId?.let {
                            Log.d("YouTubeApi", "ID of the first video: ${it?.id}")
                            Log.d("YouTubeApi", "ID of the first video: ${it?.snippet?.title}")
                            Log.d("YouTubeApi", "ID of the first video: ${it?.snippet?.description}")
                        } ?: run {
                            Log.d("YouTubeApi", "No videos found in the response")
                        }
                    } ?: run {
                        Log.d("YouTubeApi", "Response body is null")
                    }
                } else {
                    Log.d("YouTubeApi", "Response failed with code: ${response.code()} and message: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<YouTubeVideoListResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }






}