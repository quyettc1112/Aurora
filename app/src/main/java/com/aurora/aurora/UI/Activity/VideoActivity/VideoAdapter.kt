package com.aurora.aurora.UI.Activity.VideoActivity

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aurora.aurora.API_Repotitory.YoutubeAPI_Repository
import com.aurora.aurora.Model.Respone.YouTubeVideoListResponse
import com.aurora.aurora.Model.ToyModel
import com.aurora.aurora.Model.VideoModel
import com.aurora.aurora.R
import com.aurora.aurora.databinding.ActivityVideoBinding
import com.bumptech.glide.Glide
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoAdapter(val videoList: List<VideoModel>, val context: VideoActivity, youtubeapiRepository: YoutubeAPI_Repository)
    : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>()
{
    var onItemClickListener: ((VideoModel) -> Unit)? = null

    private var youtubeapiRepositoryIner: YoutubeAPI_Repository = youtubeapiRepository
    private lateinit var videoListResponseItem: YouTubeVideoListResponse.VideoItem
    private val apiKey = context.getString(R.string.API_YOUTUBE_KEY)

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val im_video = view.findViewById<ImageView>(R.id.im_video);
        val tv_video_header = view.findViewById<TextView>(R.id.tv_video_header)
        val tv_video_description = view.findViewById<TextView>(R.id.tv_video_description)
        fun bind(video: VideoModel) {
           //callYoutubeVideoList(video.id, im_video, tv_video_header, tv_video_description)

        }
    }
    private fun callYoutubeVideoList(id: String, im_video: ImageView, tv_video_header: TextView, tv_video_description: TextView) {
        youtubeapiRepositoryIner.getVideoList(
            id = id,
            apiKey = apiKey,
            part = "snippet,contentDetails"
        ).enqueue(object : Callback<YouTubeVideoListResponse> {
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
                        videoListResponseItem= youTubeVideoListResponse.items.firstOrNull()!!
                        Glide.with(context)
                            .load(videoListResponseItem.snippet.thumbnails.high.url.toString())
                            .into(im_video)

                        tv_video_header.text = videoListResponseItem.snippet.title.toString()
                        tv_video_description.text = videoListResponseItem.snippet.description.toString()
                    } ?: run {
                        Log.d("YouTubeApi", "Response body is null")
                    }
                } else {
                    Log.d("YouTubeApi", "Response failed with code: ${response.code()} and message: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<YouTubeVideoListResponse>, t: Throwable) {
                Log.d("YouTubeApi", "Call Failure")
            }
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_item_videos, parent, false)
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]
        holder.bind(video)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(video)
        }
    }

    override fun onViewRecycled(holder: VideoViewHolder) {
        super.onViewRecycled(holder)
    }

}