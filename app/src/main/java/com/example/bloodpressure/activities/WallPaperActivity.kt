package com.example.bloodpressure.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bloodpressure.R
import com.example.bloodpressure.adapters.PageAdapter
import com.example.bloodpressure.databinding.ActivityProfileDataBinding
import com.example.bloodpressure.databinding.ActivityWallPaperBinding
import com.example.bloodpressure.databinding.FragmentHistoryBinding

class WallPaperActivity : AppCompatActivity() {
    private var binding : ActivityWallPaperBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWallPaperBinding.inflate(layoutInflater)
        var view = binding!!.root
        setContentView(view)



        binding!!.backArrow.setOnClickListener{
            onBackPressed()
        }

        binding!!.viewPager.adapter = PageAdapter(supportFragmentManager)


        binding!!.tabLayout.setupWithViewPager(binding!!.viewPager)
    }
}