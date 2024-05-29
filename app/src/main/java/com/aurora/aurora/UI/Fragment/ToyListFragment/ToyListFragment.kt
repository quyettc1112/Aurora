package com.aurora.aurora.UI.Fragment.ToyListFragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionAdapter
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionInteraction
import com.aurora.aurora.Common.CommonAdapter.ToyListAdapter
import com.aurora.aurora.Common.CommonAdapter.ToyListAdapterBase
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.R
import com.aurora.aurora.Until.BottomMarginItemDecoration
import com.aurora.aurora.Until.NonScrollableGridLayoutManager
import com.aurora.aurora.databinding.FragmentHomeBinding
import com.aurora.aurora.databinding.FragmentToyListBinding
import com.google.android.play.integrity.internal.ad

class ToyListFragment : Fragment(), CategoryOptionInteraction {

    private lateinit var binding: FragmentToyListBinding
    private lateinit var categoryAdapter: CategoryOptionAdapter
    private lateinit var toyListAdapter: ToyListAdapterBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryAdapter = CategoryOptionAdapter(Constant.getListString(), this)
        toyListAdapter = ToyListAdapterBase()
        toyListAdapter.submitList(Constant.getListToys())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToyListBinding.inflate(layoutInflater, container,false)
        setCateRecycleView()
        setToyListAdapter()
        return binding.root
    }

    private fun setCateRecycleView() {
        binding.myRecyclerView.adapter = categoryAdapter
    }

    override fun setActive(position: Int) {
        val viewHolder = binding.myRecyclerView.findViewHolderForAdapterPosition(position) as? CategoryOptionAdapter.CateOptionViewHolder
        viewHolder?.setActiveItem()
    }

    private fun setToyListAdapter() {
        binding.rvToys.let {
            it.layoutManager = GridLayoutManager(requireContext(), 2)
            it.adapter = toyListAdapter
        }
        // Item Click Add To Cart
        toyListAdapter.setItemOnclickListener {
            Toast.makeText(context, "Clicked: ${it.toyName}", Toast.LENGTH_SHORT).show()
        }

        // Add To Cart Click
        toyListAdapter.onItemCartClickListener = {
            Toast.makeText(context, "Add To Cart: ${it.toyName}", Toast.LENGTH_SHORT).show()
        }

    }
    private fun searchItem() {


    }

}