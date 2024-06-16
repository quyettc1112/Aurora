package com.aurora.aurora.UI.Fragment.CartFragment

import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.PayMentActivity.PaymentActivity
import com.aurora.aurora.UI.ShareViewModel.ShareViewModel
import com.aurora.aurora.databinding.FragmentCartBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.text.DecimalFormat

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

        checkShowUI()
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
           // cartAdapter.removeItem(it.toyModel)
            sharedViewModel.removeItem(it)
            checkShowUI()
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

            val tong_tien_hang = view.findViewById<TextView>(R.id.tv_tong_tien_hang)
            tong_tien_hang?.text = ""
            tong_tien_hang?.text = "${formatPrice(cartAdapter.getTotalItemsPrice())} VND"
            // Gắn view vào dialog
            val tong_tien = view.findViewById<TextView>(R.id.tv_tong_tien_thanh_toan)
            tong_tien?.text = ""
            tong_tien?.text = "${formatPrice(cartAdapter.getTotalItemsPrice() + 20000.0)} VND"

            view.findViewById<AppCompatButton>(R.id.btn_payment).setOnClickListener {
                startActivity(Intent(requireContext(), PaymentActivity::class.java))
            }

            dialog.setContentView(view)
            dialog.show()
        }
    }

    private fun convertDpToPx(dp: Int): Int {
        val density = requireContext().resources.displayMetrics.density
        return (dp * density).toInt()
    }
    private fun checkShowUI() {
        if (cartAdapter.getCurrentList().count() > 0) {
            binding.layoutEmptyCart.visibility = View.GONE
            binding.layoutCart.visibility = View.VISIBLE
        } else {
            binding.layoutEmptyCart.visibility = View.VISIBLE
            binding.layoutCart.visibility = View.GONE
        }
    }
    fun formatPrice(price: Double): String {
        val formatter = DecimalFormat("#,###")
        return formatter.format(price)
    }
}