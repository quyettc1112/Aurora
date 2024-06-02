package com.aurora.aurora.UI.Activity.MainActivity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.Common.CommonAdapter.FragmentAdapter
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.AuthActivity.LoginActivity.LoginActivity
import com.aurora.aurora.UI.Fragment.CartFragment.CartFragment
import com.aurora.aurora.UI.Fragment.HomeFragment.HomeFragment
import com.aurora.aurora.UI.Fragment.ProfileFragment.ProfileFragment
import com.aurora.aurora.UI.Fragment.ToyListFragment.ToyListFragment
import com.aurora.aurora.UI.ShareViewModel.ShareViewModel
import com.aurora.aurora.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val sharedViewModel: ShareViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var FragmentAdapter: FragmentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpFragmentWithViewPager()
        setUpBottomNav()


    }

    private fun setUpFragmentWithViewPager() {
        val listFragment: ArrayList<Fragment> = ArrayList()
        listFragment.add(HomeFragment())
        listFragment.add(ToyListFragment())
        listFragment.add(CartFragment())
        listFragment.add(ProfileFragment())

        FragmentAdapter = FragmentAdapter(this, listFragment)
          binding.vp2Main.adapter = FragmentAdapter
          binding.vp2Main.isUserInputEnabled = false
          binding.vp2Main.offscreenPageLimit = 4

        // Settup change listener
          binding.vp2Main.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
              override fun onPageSelected(position: Int) {
                  binding.niceBottomNav.setActiveItem(position)
                  super.onPageSelected(position)
              }
          })
    }

    private fun setUpBottomNav(){
        binding.niceBottomNav.onItemSelected = {idFragemnt ->
              binding.vp2Main.setCurrentItem(idFragemnt, true)
        }

    }

    override fun onBackPressed() {
           AlertDialog.Builder(this)
            .setMessage("Bạn muốn thoát khỏi ứng dụng ?")
            .setPositiveButton("Có") { _, _ ->
                super.onBackPressed()
            }
            .setNegativeButton("Không", null)
            .show()

    }
}