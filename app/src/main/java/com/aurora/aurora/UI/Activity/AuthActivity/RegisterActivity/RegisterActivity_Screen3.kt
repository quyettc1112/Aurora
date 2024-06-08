package com.aurora.aurora.UI.Activity.AuthActivity.RegisterActivity

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aurora.aurora.AppConfig.BaseConfig.BaseActivity
import com.aurora.aurora.R
import com.aurora.aurora.UI.Activity.MainActivity.MainActivity
import com.aurora.aurora.UI.ShareViewModel.RegisterViewModel
import com.aurora.aurora.databinding.ActivityRegisterScreen3Binding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class RegisterActivity_Screen3 : BaseActivity() {
    private lateinit var binding: ActivityRegisterScreen3Binding
    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterScreen3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputValue()
    }

    private fun inputValue() {
        binding.btnRegisterScreen3.setOnClickListener {
            if (checkFieldsNotNullOrEmpty() == true && checkPhoneNumber(binding.edtRegisterPhone.text.toString())) {
                registerViewModel.updatePhone(binding.edtRegisterPhone.text.toString())
                registerViewModel.updateName(binding.edtRegisterName.text.toString())
                if (binding.edtRegisterGender.text.equals("Name")) {
                    registerViewModel.updateGender(true)
                } else registerViewModel.updateGender(false)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                Log.d("CheckValueRegisterDTO", registerViewModel.getRegisterDTO().toString())
            }
        }

        binding.customToolbarScreen3.onStartIconClick = {
            val intent = Intent(this, RegisterActivity_Screen2::class.java)
            startActivity(intent)
            finish()
        }

        binding.edtRegisterDob.setOnClickListener {
            showDatePickerDialog()
        }

        binding.edtRegisterGender.setOnClickListener {
            showGenderSelectionDialog()
        }
    }

    private fun checkFieldsNotNullOrEmpty(): Boolean {
        val name = binding.edtRegisterName.text.toString()
        val gender = binding.edtRegisterGender.text.toString()
        val dob = binding.edtRegisterDob.text.toString()
        val phone = binding.edtRegisterPhone.text.toString()

        return when {
            name.isEmpty() -> {
                binding.edtRegisterName.error = "Tên không được để trống"
                false
            }
            gender.isEmpty() -> {
                binding.edtRegisterGender.error = "Giới tính không được để trống"
                false
            }
            dob.isEmpty() -> {
                binding.edtRegisterDob.error = "Ngày sinh không được để trống"
                false
            }
            phone.isNullOrEmpty() -> {
                binding.edtRegisterPhone.error = "Số điện thoại không được để trống"
                false
            }
            else -> true
        }
    }

    private fun showDatePickerDialog() {
        // Get the current date
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Create a date picker dialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Format the date and set it to the EditText
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                binding.edtRegisterDob.setText(selectedDate)
            },
            year, month, day
        )
        datePickerDialog.show()
    }

    private fun showGenderSelectionDialog() {
        // Các tùy chọn giới tính
        val genderOptions = arrayOf("Nam", "Nữ", "Khác")

        // Tạo dialog
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Chọn Giới Tính")
        builder.setItems(genderOptions) { dialog, which ->
            // Thiết lập giá trị đã chọn vào EditText
            binding.edtRegisterGender.setText(genderOptions[which])
        }

        // Hiển thị dialog
        builder.show()
    }

    fun checkPhoneNumber(phone: String): Boolean {
        return if (phone.length == 10) {
            true
        } else {
            binding.edtRegisterPhone.error = "Nhập Số Điện Thoại Hợp Lệ"
            false
        }
    }
}