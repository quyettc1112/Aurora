package com.aurora.aurora.Common.CommonAdapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aurora.aurora.Model.ToyModel
import com.aurora.aurora.R

class CategoryOptionAdapter(private val items: List<CategoryString>,  private val interaction: CategoryOptionInteraction)
    : RecyclerView.Adapter<CategoryOptionAdapter.CateOptionViewHolder>()
{
    var onItemClickListenerID: ((Int) -> Unit)? = null
    var activePosition = 0
    inner class CateOptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.text)
        fun bind(content: CategoryOptionAdapter.CategoryString,  isActive: Boolean) {
            title.text = content.title
            if (isActive) {
                setActiveItem()
            } else {
                setInactiveItem()
            }
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                notifyItemChanged(activePosition)
                activePosition = adapterPosition
                notifyItemChanged(activePosition)
                interaction.setActive(adapterPosition)
                onItemClickListenerID?.let { it1 -> it1(position) }
            }
        }

        fun setActiveItem() {
            // Set text color
            title.setTextColor(Color.WHITE)
            // Set background tint (requires API level 21+)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                title.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(title.context, R.color.redPrimary))
            }
        }
        fun setInactiveItem() {
            title.setTextColor(Color.BLACK)  // Set the text color to black
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                title.backgroundTintList = null
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CateOptionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return CateOptionViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CateOptionViewHolder, position: Int) {
        val isActive = position == activePosition
        val item = items[position]
        holder.bind(item, isActive)
    }

    fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }
    data class CategoryString(val title: String)
}


