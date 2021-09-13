package com.example.demo_basic_staff_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //use this when binding with mainactivity.xml
        //val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)


        // and use this  when u don't use binding with main_activity.xml
        setContentView(R.layout.activity_main)
    }
}