package com.aurora.aurora.Common.CommonAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.aurora.aurora.AppConfig.BaseConfig.BaseAdapter
import com.aurora.aurora.AppConfig.BaseConfig.BaseItemViewHolderCF
import com.aurora.aurora.Model.ImageReleventModel
import com.aurora.aurora.Model.ToyModel
import com.aurora.aurora.databinding.ItemImageRelveventBinding
import com.aurora.aurora.databinding.RecycleItemToyListBinding

class ImageReleventAdapter: BaseAdapter<ImageReleventModel, ImageReleventAdapter.ItemReleventViewHolder>() {
    var onItemImageClickListener: ((ImageReleventModel) -> Unit)? = null
    inner class ItemReleventViewHolder( binding: ItemImageRelveventBinding):  BaseItemViewHolderCF<ImageReleventModel, ItemImageRelveventBinding>(binding)  {
        override fun bind(item: ImageReleventModel) {
            binding.imToyImage.setImageResource(item.image)
            binding.imToyImage.setOnClickListener {
                onItemImageClickListener?.let { it1 -> it1(item) }
            }
        }
    }

    override fun differCallBack(): DiffUtil.ItemCallback<ImageReleventModel> {
        return object : DiffUtil.ItemCallback<ImageReleventModel>() {
            override fun areItemsTheSame(oldItem: ImageReleventModel, newItem: ImageReleventModel): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: ImageReleventModel, newItem: ImageReleventModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemReleventViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImageRelveventBinding.inflate(inflater, parent, false)
        return ItemReleventViewHolder(binding)
    }
}