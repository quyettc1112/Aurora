package com.aurora.aurora.UI.Fragment.ProfileFragment

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.AuthActivity.LoginActivity.LoginActivity
import com.aurora.aurora.databinding.ActivityRegisterBinding
import com.aurora.aurora.databinding.FragmentHomeBinding
import com.aurora.aurora.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        binding.logout.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            requireActivity().startActivity(intent)
            requireActivity().finish()
        }
        return binding.root
    }
}