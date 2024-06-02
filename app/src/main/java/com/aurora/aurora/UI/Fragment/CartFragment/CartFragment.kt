package com.aurora.aurora.UI.Fragment.CartFragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.R
import com.aurora.aurora.databinding.FragmentCartBinding

class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding

    private lateinit var cartAdapter: CartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cartAdapter = CartAdapter()
        cartAdapter.submitList(Constant.getListCart())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(layoutInflater, container, false);

        binding.rlCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rlCart.adapter = cartAdapter



        return binding.root
    }
}