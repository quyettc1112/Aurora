package com.aurora.aurora.UI.Fragment.ProfileFragment

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.activityViewModels
import com.aurora.aurora.API_Repotitory.UserAPI_Repository
import com.aurora.aurora.Common.TokenManager.TokenManager
import com.aurora.aurora.Model.Respone.UserInfoRespone
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.AuthActivity.LoginActivity.LoginActivity
import com.aurora.aurora.UI.ShareViewModel.ShareViewModel
import com.aurora.aurora.databinding.ActivityRegisterBinding
import com.aurora.aurora.databinding.FragmentHomeBinding
import com.aurora.aurora.databinding.FragmentProfileBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    @Inject
    lateinit var userapiRepository: UserAPI_Repository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)

        binding.logout.setOnClickListener {


            showLogoutDialog()
        }

        binding.laIntentToFacebook.setOnClickListener {
            val facebookUrl = "https://www.facebook.com/AuroraToys68"
            val facebookIntent = Intent(Intent.ACTION_VIEW)

            try {
                val packageManager = context?.packageManager
                // Kiểm tra xem Facebook có được cài đặt hay không
                if (packageManager != null) {
                    packageManager.getPackageInfo("com.facebook.katana", 0)
                }
                val uri = Uri.parse("fb://facewebmodal/f?href=$facebookUrl")
                facebookIntent.data = uri
            } catch (e: PackageManager.NameNotFoundException) {
                // Nếu Facebook không được cài đặt, mở bằng trình duyệt
                facebookIntent.data = Uri.parse(facebookUrl)
            }
            startActivity(facebookIntent)
        }

        callGetUserInfo()

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
        val titlt = view.findViewById<TextView>(R.id.tv_title)
        val des = view.findViewById<TextView>(R.id.tv_desctip)

        if(TokenManager.getAccessToken(requireContext()) != null) {
            titlt.text = "Đăng Xuất"
            des.text = "Bạn chắc chắn muốn đăng xuất ?"
        } else {
            titlt.text = "Đăng Nhập"
            des.text = "Đăng nhập tài khoản của bạn?"

        }




        buttonYes.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            requireActivity().startActivity(intent)
            dialog.dismiss()
            requireActivity().finish()
            TokenManager.removeAccessToken(requireContext())
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



    private fun callGetUserInfo() {
        val token = TokenManager.getAccessToken(requireContext());
        if (!token.isNullOrEmpty()) {
            userapiRepository.callGetUserInfo(authorization = "Bearer ${token}").enqueue(object : Callback<UserInfoRespone> {
                override fun onResponse(
                    call: Call<UserInfoRespone>,
                    response: Response<UserInfoRespone>
                ) {
                    if(response.isSuccessful) {
                       binding.userName.text = response.body()?.name.toString()
                        binding.ivUserAvatar.setImageResource(R.drawable.ic_user_default);
                        binding.logout.text = "Đăng Xuất"
                        TokenManager.savePhoneNumber(requireContext(), response.body()?.phoneNumber.toString())

                    } else {
                        Log.d("CheckUSerInfo", response.code().toString())
                        Log.d("CheckUSerInfo", response.message().toString())
                        Log.d("CheckUSerInfo", response.errorBody().toString())

                    }
                }

                override fun onFailure(call: Call<UserInfoRespone>, t: Throwable) {
                    Log.d("CheckUSerInfo",t.message.toString())
                }

            })

        } else {
            binding.logout.text = "Đăng Nhập"
            binding.ivUserAvatar.setImageResource(0);
            binding.userName.text = "Bạn Chưa Đăng Nhập"

        }




    }
}