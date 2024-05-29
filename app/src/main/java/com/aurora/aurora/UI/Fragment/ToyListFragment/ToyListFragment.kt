package com.aurora.aurora.UI.Fragment.ToyListFragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionAdapter
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionInteraction
import com.aurora.aurora.Common.CommonAdapter.ToyListAdapterBase
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.Model.ToyModel
import com.aurora.aurora.databinding.FragmentToyListBinding

class ToyListFragment : Fragment(), CategoryOptionInteraction {
    private lateinit var binding: FragmentToyListBinding
    private lateinit var categoryAdapter: CategoryOptionAdapter
    private lateinit var toyListAdapter: ToyListAdapterBase
    private lateinit var toyListViewModel: ToyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categoryAdapter = CategoryOptionAdapter(Constant.getListString(), this)

        toyListViewModel = ViewModelProvider(this).get(ToyListViewModel::class.java)
        toyListViewModel.setToyList(Constant.getListToys())

        toyListAdapter = ToyListAdapterBase()
        toyListAdapter.submitList(toyListViewModel.currentToyList.value as MutableList<ToyModel>)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToyListBinding.inflate(layoutInflater, container,false)
        setCateRecycleView()
        setToyListAdapter()
        observeViewModel()
        searchItem()
        clickPopularProduct()
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
        binding.edtSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val inputText = s.toString()
                toyListViewModel.filterToyList(inputText)
            }
        })
    }

    private fun observeViewModel() {
        toyListViewModel.currentToyList.observe(viewLifecycleOwner, Observer { toyList ->
            toyListAdapter.submitList(toyList)
        })
    }

    private fun clickPopularProduct() {
        categoryAdapter.onItemClickListenerID = { position ->
            Toast.makeText(requireContext(), position.toString(), Toast.LENGTH_SHORT).show()
        }


    }

}