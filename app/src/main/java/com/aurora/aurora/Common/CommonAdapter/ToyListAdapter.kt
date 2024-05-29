package com.aurora.aurora.Common.CommonAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aurora.aurora.Model.ToyModel
import com.aurora.aurora.R
import java.text.DecimalFormat

class ToyListAdapter(private val toys: List<ToyModel> )
    : RecyclerView.Adapter<ToyListAdapter.ToyListViewHolder>()
{
        var onItemClickListener: ((ToyModel) -> Unit)? = null
        var onItemCartClickListener: ((ToyModel) -> Unit)? = null

        inner class ToyListViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val toyImageView: ImageView = view.findViewById(R.id.im_toy_image)
            val tv_toy_price: TextView = view.findViewById(R.id.tv_toy_price)
            val tv_toy_name: TextView = view.findViewById(R.id.tv_toy_name)
            val tv_star_rating: TextView = view.findViewById(R.id.tv_star_rating)
            val la_addToCarts: LinearLayout = view.findViewById(R.id.la_addToCarts) // Thêm view này

            fun bind(toy: ToyModel) {
                toyImageView.setImageResource(R.drawable.ic_material_toy)
                tv_toy_price.text =  "${formatPrice(toy.toyPrice)} VND"
                tv_toy_name.text = toy.toyName
                tv_star_rating.text = "(${toy.toyRating.toString()})"
                la_addToCarts.setOnClickListener {
                    onItemCartClickListener?.let { it1 -> it1(toy) }
                }
            }

            fun formatPrice(price: Double): String {
                val formatter = DecimalFormat("#,###")
                return formatter.format(price)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToyListViewHolder {
        return ToyListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_item_toy_list, parent, false)
        )
    }

    override fun getItemCount(): Int = toys.size

    override fun onBindViewHolder(holder: ToyListViewHolder, position: Int) {
        val item = toys[position]
        holder.bind(item)
        
    }

}