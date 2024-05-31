package com.aurora.aurora.Common.CommonAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.aurora.aurora.AppConfig.BaseConfig.BaseAdapter
import com.aurora.aurora.AppConfig.BaseConfig.BaseItemViewHolderCF
import com.aurora.aurora.Model.ToyModel
import com.aurora.aurora.R
import com.aurora.aurora.databinding.RecycleItemToyListBinding
import java.text.DecimalFormat

class ToyListAdapterBase : BaseAdapter<ToyModel, ToyListAdapterBase.ToyBaseItemViewHolder>(){

    var onItemCartClickListener: ((ToyModel) -> Unit)? = null
    inner class ToyBaseItemViewHolder(binding: RecycleItemToyListBinding): BaseItemViewHolderCF<ToyModel, RecycleItemToyListBinding>(binding) {
        override fun bind(item: ToyModel) {
            binding.imToyImage.setImageResource(item.toyImage)
            binding.tvToyPrice.text =  "${formatPrice(item.toyPrice)} VND"
            binding.tvToyName.text = item.toyName
            binding.tvStarRating.text = "(${item.toyRating.toString()})"
            binding.laAddToCarts.setOnClickListener {
                onItemCartClickListener?.let { it1 -> it1(item) }
            }

        }
        fun formatPrice(price: Double): String {
            val formatter = DecimalFormat("#,###")
            return formatter.format(price)
        }
    }

    override fun differCallBack(): DiffUtil.ItemCallback<ToyModel> {
        return object : DiffUtil.ItemCallback<ToyModel>() {
            override fun areItemsTheSame(oldItem: ToyModel, newItem: ToyModel): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: ToyModel, newItem: ToyModel): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToyBaseItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecycleItemToyListBinding.inflate(inflater, parent, false)
        return ToyBaseItemViewHolder(binding)
    }

}

