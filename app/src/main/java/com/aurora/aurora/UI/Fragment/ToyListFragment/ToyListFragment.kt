package com.aurora.aurora.UI.Fragment.ToyListFragment

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionAdapter
import com.aurora.aurora.Common.CommonAdapter.CategoryOptionInteraction
import com.aurora.aurora.Common.CommonAdapter.ToyListAdapterBase
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.Model.CartModel
import com.aurora.aurora.Model.ToyModel
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.ProductDetailActivity.ProductDetailActivity
import com.aurora.aurora.UI.ShareViewModel.ShareViewModel
import com.aurora.aurora.databinding.FragmentToyListBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class ToyListFragment : Fragment(), CategoryOptionInteraction {
    private lateinit var binding: FragmentToyListBinding
    private lateinit var categoryAdapter: CategoryOptionAdapter
    private lateinit var toyListAdapter: ToyListAdapterBase
    private lateinit var toyListViewModel: ToyListViewModel

    private val sharedViewModel: ShareViewModel by activityViewModels()

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
        showFillterDialog()
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
        // Item Click Product Detail
        toyListAdapter.setItemOnclickListener {
            Toast.makeText(context, "Clicked: ${it.idDb}", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireContext(), ProductDetailActivity::class.java)
            intent.putExtra("product_id", it.id)
            requireContext().startActivity(intent)
        }

        // Add To Cart Click
        toyListAdapter.onItemCartClickListener = {
            sharedViewModel.addItem(CartModel.create(it, 1))
        }
    }
    private fun searchItem() {
        binding.edtSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                toyListViewModel.setCurrentSearchValue(query)
                toyListViewModel.filterToyList(query = query)

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
            toyListViewModel.setCurrentPopular(position);
            toyListViewModel.filterToyList(toyListViewModel.currentSearchLiveData.value.toString())
        }
    }

    private fun showFillterDialog() {
        binding.imFillter.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.dialog_fillter_toys, null)
            dialogView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                convertDpToPx(670)
            )
            val dialog = BottomSheetDialog(requireContext())
            dialog.setContentView(dialogView)
            // Turn Off Dialog
            dialogView.findViewById<ImageView>(R.id.im_dissmissDialog).setOnClickListener {
                dialog.dismiss()
            }

            // Initialize TextViews
            val tv_g_all = dialogView.findViewById<TextView>(R.id.tv_g_all)
            val tv_g_nam = dialogView.findViewById<TextView>(R.id.tv_g_nam)
            val tv_g_nu = dialogView.findViewById<TextView>(R.id.tv_g_nu)

            // Set OnClickListener for each TextView
            tv_g_all.setOnClickListener(onClickListener)
            tv_g_nam.setOnClickListener(onClickListener)
            tv_g_nu.setOnClickListener(onClickListener)


            dialog.show()
        }
    }

    private val onClickListener = View.OnClickListener { view ->
        val dialogView = (view.parent as ViewGroup)
        val textViewTatCa = dialogView.findViewById<TextView>(R.id.tv_g_all)
        val textViewNam = dialogView.findViewById<TextView>(R.id.tv_g_nam)
        val textViewNu = dialogView.findViewById<TextView>(R.id.tv_g_nu)

        // Set all TextViews to inactive
        setInactive(textViewTatCa)
        setInactive(textViewNam)
        setInactive(textViewNu)

        setActive(view as TextView)
    }

    private fun setActive(textView: TextView) {
        textView.setTextColor(Color.WHITE)
        // Set background tint (requires API level 21+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textView.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(textView.context, R.color.redPrimary))
        }
    }

    private fun setInactive(textView: TextView) {
        textView.setTextColor(Color.BLACK)  // Set the text color to black
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textView.backgroundTintList = null
        }
    }

    private fun convertDpToPx(dp: Int): Int {
        val density = requireContext().resources.displayMetrics.density
        return (dp * density).toInt()
    }




}