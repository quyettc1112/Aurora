package com.aurora.aurora.UI.Activity.StartActivity

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.Common.CommonAdapter.IntroSliderAdapter
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.MainActivity.MainActivity
import com.aurora.aurora.databinding.ActivityStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : BaseActivity() {
    private lateinit var binding: ActivityStartBinding
    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSliderAdapter.IntroSlide(
                title = "Auty E-Learning",
                description = "Bắt đầu hành trình học tập số với Auty và thiết lập thông tin cá nhân cho trải nghiệm tốt nhất.",
                image = R.drawable.ic_material_start_1,
                imageDecor = R.drawable.decor_splash
            ),
            IntroSliderAdapter.IntroSlide(
                title = "Khoá Học Phong Phú",
                description = "Tiếp cận hàng trăm khoá học được thiết kế đặc biệt cho mọi lứa tuổi và năng lực học tập",
                image = R.drawable.ic_material_start_2,
                imageDecor = R.drawable.decor_splash2
            ),
            IntroSliderAdapter.IntroSlide(
                title = "Học Mọi Lúc, Mọi Nơi",
                description = "Tận dụng các tài nguyên học tập đa dạng trên mọi thiết bị, giúp con bạn học mọi lúc mọi nơi.",
                image = R.drawable.ic_material_start_3,
                imageDecor = R.drawable.image_decor_rm_bg
            )
        ), this
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setIntroSlider()
        setIndicator()
        setCurrentIndicator(0)
        callEventChangeViewPager()
        clickButtonNext()
    }


    private fun clickButtonNext() {
        binding.btnNext.setOnClickListener {
            if (binding.introSliderViewpager.currentItem + 1  < introSliderAdapter.itemCount) {
                binding.introSliderViewpager.currentItem += 1
            } else {
                Intent(this, MainActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }
    private fun callEventChangeViewPager() {
        binding.introSliderViewpager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
    }

    private fun setIntroSlider() {
        binding.introSliderViewpager.adapter = introSliderAdapter
    }

    private fun setIndicator() {
        val indicator = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(15,0,15,0)
        for (i in indicator.indices) {
            indicator[i] = ImageView(applicationContext)
            indicator[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
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
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

    }
}