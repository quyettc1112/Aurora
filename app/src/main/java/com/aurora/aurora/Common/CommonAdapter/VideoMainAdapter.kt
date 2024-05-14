package com.aurora.aurora.Common.CommonAdapter

import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.aurora.aurora.R
import java.text.DecimalFormat

class VideoMainAdapter(private val courseList: List<Course>): RecyclerView.Adapter<VideoMainAdapter.CourseMainViewHodler>() {

    var onItemClick: ((VideoMainAdapter.Course) -> Unit)? = null
    inner class CourseMainViewHodler(view: View): RecyclerView.ViewHolder(view){
        private val image = view.findViewById<ImageView>(R.id.iv_course_main)
        private val title = view.findViewById<TextView>(R.id.tv_course_title_main)
/*
        private val originalPrice = view.findViewById<TextView>(R.id.tv_course_originalPrice_main)
        private val discountedPrice = view.findViewById<TextView>(R.id.tv_course_discountedPrice_main)
        private val rating = view.findViewById<RatingBar>(R.id.ratingbar_main)
        private val reviews = view.findViewById<TextView>(R.id.tv_numRating_main)
*/

        fun bind(course: Course) {
            image.setImageResource(course.image)
            title.text = course.title
        /*    originalPrice.text =  "${formatPrice(course.originalPrice)} VND"
            val spannableString = SpannableString(originalPrice.text)
            spannableString.setSpan(StrikethroughSpan(), 0, originalPrice.text.length, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE)
            originalPrice.text = spannableString

            discountedPrice.text = "${formatPrice(course.discountedPrice)} VND"
            rating.rating = course.rating.toFloat()
            reviews.text ="(${course.reviews})"*/
        }
        fun formatPrice(price: Double): String {
            val formatter = DecimalFormat("#,###")
            return formatter.format(price)
        }
    }

    data class Course(
        val image: Int,
        val title: String,
        val originalPrice: Double,
        val discountedPrice: Double,
        val rating: Double,
        val reviews: Int,
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseMainViewHodler {
        return CourseMainViewHodler(
            LayoutInflater.from(parent.context).inflate(R.layout.recycle_item_course, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: CourseMainViewHodler, position: Int) {
        holder.bind(courseList[position])
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(courseList[position])
        }
    }
}