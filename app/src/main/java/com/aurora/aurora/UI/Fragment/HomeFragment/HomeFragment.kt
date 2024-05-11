package com.aurora.aurora.UI.Fragment.HomeFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurora.aurora.Common.CommonAdapter.VideoMainAdapter
import com.aurora.aurora.Common.Constant.Constant
import com.aurora.aurora.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var videoAdapter: VideoMainAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        videoAdapter = VideoMainAdapter(Constant.getListCourse())
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        setUpVideoMainRecycleView()

        return binding.root
    }




    private fun setUpVideoMainRecycleView(){
        binding.rvVideo.adapter = videoAdapter
    }
}