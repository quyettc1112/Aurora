package com.aurora.aurora.UI.Fragment.CartFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.DifferCallback
import androidx.recyclerview.widget.DiffUtil
import com.aurora.aurora.AppConfig.BaseConfig.BaseAdapter
import com.aurora.aurora.AppConfig.BaseConfig.BaseItemViewHolderCF
import com.aurora.aurora.Model.CartModel
import com.aurora.aurora.Model.ToyModel
import com.aurora.aurora.databinding.ItemToyCartBinding
import com.google.android.play.integrity.internal.i
import java.text.DecimalFormat

class CartAdapter(): BaseAdapter<CartModel, CartAdapter.CartAdapterViewHoilder>(){
    var onAddQuantityItemClickListener: ((CartModel) -> Unit)? = null
    var onRemoveQuantityItemClickListener: ((CartModel) -> Unit)? = null

    inner class CartAdapterViewHoilder(binding: ItemToyCartBinding): BaseItemViewHolderCF<CartModel,ItemToyCartBinding>(binding) {
        override fun bind(item: CartModel) {
            binding.imItemCartImage.setImageResource(item.toyModel.toyImage)
            binding.tvItemCartProductName.text = item.toyModel.toyName
            binding.tvItemCartProductPrice.text =  "${formatPrice(item.toyModel.toyPrice)} VND"
            binding.tvItemCartProductCategory.text = item.toyModel.categoryModel.name
            binding.tvItemCartProductQuantity.text = item.quantity.toString()
            binding.imPlusQuantity.setOnClickListener {
                onAddQuantityItemClickListener.let { it -> it?.invoke(item) }
                binding.tvItemCartProductQuantity.text = item.quantity.toString()
            }
            binding.imMinusQuantity.setOnClickListener {
                onRemoveQuantityItemClickListener.let { it -> it?.invoke(item) }
                binding.tvItemCartProductQuantity.text = item.quantity.toString()
            }
        }

        fun formatPrice(price: Double): String {
            val formatter = DecimalFormat("#,###")
            return formatter.format(price)
        }
    }

    fun addItem(toyModel: ToyModel) {
        val currentList = differ.currentList.toMutableList()
        val existingItem = currentList.find { it.toyModel.id == toyModel.id }
        if (existingItem != null) {
            existingItem.quantity += 1
        } else {
            currentList.add(CartModel.create(toyModel, 1))
        }
        differ.submitList(currentList)
    }

    fun removeItem(toyModel: ToyModel) {
        val currentList = differ.currentList.toMutableList()
        val existingItem = currentList.find { it.toyModel.id == toyModel.id }
        if (existingItem != null) {
            if (existingItem.quantity > 1) {
                existingItem.quantity -= 1
            } else {
                currentList.remove(existingItem)
            }
        }
        differ.submitList(currentList)
    }

    fun getTotalItems(): Int {
        return differ.currentList.sumOf { it.quantity }
    }

    fun getTotalItemsPrice(): Double {
        return differ.currentList.sumOf { it.toyModel.toyPrice * it.quantity }
    }

    fun getCurrentList(): MutableList<CartModel> {
        return differ.currentList
    }

    fun updateCartItems(newCartItems: MutableList<CartModel>) {
        differ.submitList(newCartItems)
        notifyDataSetChanged()
    }
    override fun differCallBack(): DiffUtil.ItemCallback<CartModel> {
        return object : DiffUtil.ItemCallback<CartModel> () {
            override fun areItemsTheSame(oldItem: CartModel, newItem: CartModel): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: CartModel, newItem: CartModel): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapterViewHoilder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemToyCartBinding.inflate(inflater, parent, false)
        return CartAdapterViewHoilder(binding)
    }




}