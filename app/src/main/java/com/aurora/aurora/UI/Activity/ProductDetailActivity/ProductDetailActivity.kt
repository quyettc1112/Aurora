package com.aurora.aurora.UI.Activity.ProductDetailActivity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.Common.CommonAdapter.ImageReleventAdapter
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.Model.ImageReleventModel
import com.aurora.aurora.Model.ToyModel
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.MainActivity.MainActivity
import com.aurora.aurora.databinding.ActivityProductDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class ProductDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityProductDetailBinding
    private lateinit var toyModel: ToyModel

    private lateinit var imageReleventAdapter: ImageReleventAdapter
    private var listItemRelevent: ArrayList<ImageReleventModel> = ArrayList<ImageReleventModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setUpImageRelevent()
        backToPreviousActivity()
        bindDataProductDetail()
    }

    private fun setUpImageRelevent() {
        imageReleventAdapter = ImageReleventAdapter()
        getToyModelByIDFromConstans()

        if (toyModel != null) {
            listItemRelevent.add(ImageReleventModel(0, toyModel.listImage.get(0)))
            listItemRelevent.add(ImageReleventModel(1, toyModel.listImage.get(1)))
            listItemRelevent.add(ImageReleventModel(2, toyModel.listImage.get(2)))
            imageReleventAdapter.submitList(listItemRelevent)
            binding.rlImageRelevent.adapter = imageReleventAdapter
            imageReleventAdapter.onItemImageClickListener = {
                binding.imToyImage.setImageResource(it.image)
            }
        }
    }
    private fun backToPreviousActivity() {
        binding.customToolbar2.onStartIconClick = {
            finish()
        }
    }

    private fun getToyModelByIDFromConstans() {
        val id = intent.getIntExtra("product_id", 0);
        toyModel = Constant.getListToys().get(id)
    }

    private fun bindDataProductDetail() {
        binding.tvProductPrice.text = "${formatPrice(toyModel.toyPrice)} VND"
        binding.tvProductCategory.text = toyModel.categoryModel.name
        binding.tvProductName.text = toyModel.toyName
        binding.tvProductDescription.text = toyModel.toyDescription
        binding.imToyImage.setImageResource(toyModel.toyImage)
    }

    fun formatPrice(price: Double): String {
        val formatter = DecimalFormat("#,###")
        return formatter.format(price)
    }


}