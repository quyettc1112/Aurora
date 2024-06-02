package com.aurora.aurora.UI.Fragment.CartFragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.R
import com.aurora.aurora.UI.ShareViewModel.ShareViewModel
import com.aurora.aurora.databinding.FragmentCartBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var cartAdapter: CartAdapter
    private val sharedViewModel: ShareViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cartAdapter = CartAdapter()
        cartAdapter.submitList(sharedViewModel.cartItems.value ?: mutableListOf())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false);

        binding.rlCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rlCart.adapter = cartAdapter

        setAddOrRemoveQuantity()
        observeViewModel()
        showPaymentDialog()
        return binding.root
    }

    private fun setAddOrRemoveQuantity() {
        cartAdapter.onAddQuantityItemClickListener = {
            Toast.makeText(requireContext(), "Add", Toast.LENGTH_SHORT).show()
            cartAdapter.addItem(it.toyModel)
        }

        cartAdapter.onRemoveQuantityItemClickListener = {
            Toast.makeText(requireContext(), "Minus", Toast.LENGTH_SHORT).show()
            cartAdapter.removeItem(it.toyModel)
        }
    }

    private fun observeViewModel() {
        sharedViewModel.cartItems.observe(viewLifecycleOwner, Observer { cartItems ->
            cartAdapter.updateCartItems(cartItems)
            checkShowUI()
        })
    }

    private fun showPaymentDialog() {
        binding.btnPayment.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_proceed_to_payment, null)
            view.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                convertDpToPx(430)
            )
            // Gắn view vào dialog
            dialog.setContentView(view)
            dialog.show()
        }
    }

    private fun convertDpToPx(dp: Int): Int {
        val density = requireContext().resources.displayMetrics.density
        return (dp * density).toInt()
    }
    private fun checkShowUI() {
        if (sharedViewModel.cartItems.value?.size == 0) {
            binding.ltEmptyCart.visibility = View.VISIBLE
            binding.layoutCart.visibility = View.GONE
        } else {
            binding.ltEmptyCart.visibility = View.GONE
            binding.layoutCart.visibility = View.VISIBLE
        }
    }
}