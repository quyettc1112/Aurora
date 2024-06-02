package com.aurora.aurora.UI.Fragment.ProfileFragment

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.AuthActivity.LoginActivity.LoginActivity
import com.aurora.aurora.databinding.ActivityRegisterBinding
import com.aurora.aurora.databinding.FragmentHomeBinding
import com.aurora.aurora.databinding.FragmentProfileBinding
import com.google.android.material.bottomsheet.BottomSheetDialog

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


            showLogoutDialog()
        }

        return binding.root
    }

    private fun showLogoutDialog() {
        val dialog = BottomSheetDialog(requireContext())
        val view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_bottom_sheet_logout_ui, null)

        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            convertDpToPx(300)
        )


        // Gắn view vào dialog
        dialog.setContentView(view)

        // Tìm các view con và thiết lập sự kiện
        val buttonYes = view.findViewById<AppCompatButton>(R.id.btn_logout_accept)
        val buttonNo = view.findViewById<AppCompatButton>(R.id.btn_logout_cancle)

        buttonYes.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            requireActivity().startActivity(intent)
            dialog.dismiss()
            requireActivity().finish()
        }

        buttonNo.setOnClickListener {
            // Đóng dialog khi nhấn nút No
            dialog.dismiss()
        }

        // Hiển thị dialog
        dialog.show()


    }

    private fun convertDpToPx(dp: Int): Int {
        val density = requireContext().resources.displayMetrics.density
        return (dp * density).toInt()
    }
}