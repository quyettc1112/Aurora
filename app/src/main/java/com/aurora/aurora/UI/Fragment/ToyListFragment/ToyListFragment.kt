package com.aurora.aurora.UI.Fragment.ToyListFragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionAdapter
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionInteraction
import com.aurora.aurora.Common.CommonAdapter.ToyListAdapter
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.R
import com.aurora.aurora.Until.BottomMarginItemDecoration
import com.aurora.aurora.Until.NonScrollableGridLayoutManager
import com.aurora.aurora.databinding.FragmentHomeBinding
import com.aurora.aurora.databinding.FragmentToyListBinding

class ToyListFragment : Fragment(), CategoryOptionInteraction {

    private lateinit var binding: FragmentToyListBinding
    private lateinit var categoryAdapter: CategoryOptionAdapter
    private lateinit var toyListAdapter: ToyListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryAdapter = CategoryOptionAdapter(Constant.getListString(), this)
        toyListAdapter = ToyListAdapter(Constant.getListToys())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToyListBinding.inflate(layoutInflater, container,false)
        setCateRecycleView()
        setToysListRecycleView()
        return binding.root
    }

    private fun setCateRecycleView() {
        binding.myRecyclerView.adapter = categoryAdapter

    }

    override fun setActive(position: Int) {
        val viewHolder = binding.myRecyclerView.findViewHolderForAdapterPosition(position) as? CategoryOptionAdapter.CateOptionViewHolder
        viewHolder?.setActiveItem()
    }

    private fun setToysListRecycleView() {
        binding.rvToys.let {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.adapter = toyListAdapter
           /* val bottomMarginInPx = convertDpToPx(20)
            it.addItemDecoration(BottomMarginItemDecoration(bottomMarginInPx))
            val itemCount = toyListAdapter?.itemCount ?: 0
            val rowCount = if (itemCount % 2 == 0) itemCount / 2 else (itemCount / 2) + 1
            val newHeight = rowCount * convertDpToPx(270) + (rowCount - 1) * bottomMarginInPx
            it.layoutParams = it.layoutParams.apply {
                height = newHeight
            }*/
        }
    }
    private fun convertDpToPx(dp: Int): Int {
        val density = requireContext().resources.displayMetrics.density
        return (dp * density).toInt()
    }
}