package com.aurora.aurora.UI.Activity.MainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aurora.aurora.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}