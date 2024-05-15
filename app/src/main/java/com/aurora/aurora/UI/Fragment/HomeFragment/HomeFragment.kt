package com.aurora.aurora.UI.Fragment.HomeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionAdapter
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionInteraction
import com.aurora.aurora.Common.CommonAdapter.VideoMainAdapter
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.R
import com.aurora.aurora.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), CategoryOptionInteraction {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var videoAdapter: VideoMainAdapter
    private lateinit var cateOptionAdapter: CategoryOptionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoAdapter = VideoMainAdapter(Constant.getListCourse())
        cateOptionAdapter = CategoryOptionAdapter(Constant.getListString(), this)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setUpVideoMainRecycleView()

        setIndicator()
        setCurrentIndicator(0)

        setRecycleCateOption()

        return binding.root
    }

    private fun setRecycleCateOption() {
        binding.myRecyclerView.adapter = cateOptionAdapter


    }




    private fun setUpVideoMainRecycleView(){
        binding.rvVideo.adapter = videoAdapter

        binding.rvVideo.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                setCurrentIndicator(firstVisibleItemPosition)
            }
        })
    }


    private fun setIndicator() {
        val indicator = arrayOfNulls<ImageView>(videoAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(15,0,15,0)
        for (i in indicator.indices) {
            indicator[i] = ImageView(requireContext())
            indicator[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorContainer.addView(indicator[i])
        }

    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = binding.indicatorContainer.childCount
        for (i in 0 until  childCount) {
            val imageView = binding.indicatorContainer[i] as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

    }

    override fun setActive(position: Int) {
        val viewHolder = binding.myRecyclerView.findViewHolderForAdapterPosition(position) as? CategoryOptionAdapter.CateOptionViewHolder
        viewHolder?.setActiveItem()
    }
}