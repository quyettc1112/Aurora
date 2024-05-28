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
        fun bind(video: VideoModel) {

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

    }

}