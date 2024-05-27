package com.aurora.aurora.UI.Activity.VideoActivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aurora.aurora.Model.VideoModel
import com.aurora.aurora.R
import com.aurora.aurora.databinding.ActivityVideoBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class VideoAdapter(val videoList: List<VideoModel>, val context: VideoActivity, val binding: ActivityVideoBinding)
    : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>()

{

    inner class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val playerView: PlayerView = view.findViewById(R.id.pl_video)
        val titleTextView: TextView = view.findViewById(R.id.tv_video_header)
        val tv_video_description: TextView = view.findViewById(R.id.tv_video_description)
        var player: ExoPlayer? = null

        fun bind(video: VideoModel) {
            titleTextView.text = video.titleVideo
            tv_video_description.text = video.descriptionVideo
           // initializePlayer(video.uriVideo)
            player = ExoPlayer.Builder(itemView.context).build().also { exoPlayer ->
                playerView.player = exoPlayer
                val mediaItem = MediaItem.fromUri(video.uriVideo)
                exoPlayer.setMediaItem(mediaItem)
                exoPlayer.prepare()
                exoPlayer.playWhenReady = false
            }
        }
        fun releasePlayer() {
            player?.release()
            player = null
        }
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
    }

    override fun onViewRecycled(holder: VideoViewHolder) {
        super.onViewRecycled(holder)
        holder.releasePlayer()
    }

}