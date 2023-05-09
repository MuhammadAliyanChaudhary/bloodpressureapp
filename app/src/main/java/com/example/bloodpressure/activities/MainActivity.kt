package com.example.bloodpressure.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bloodpressure.R
import com.example.bloodpressure.databinding.ActivityMainBinding
import com.example.bloodpressure.fragments.HistoryFragment
import com.example.bloodpressure.fragments.InfoFragment
import com.example.bloodpressure.fragments.SettingsFragment
import com.example.bloodpressure.fragments.TrackerFragment

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding!!.root
        setContentView(view)

        //Default fragment tracker fragment
        replaceFragment(TrackerFragment())




        // select navigation
        binding!!.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.trackerFragment -> replaceFragment(TrackerFragment())
                R.id.historyFragment -> replaceFragment(HistoryFragment())
                R.id.infoFragment -> replaceFragment(InfoFragment())
                R.id.settings -> replaceFragment(SettingsFragment())

                else -> {}
            }
            true
        }





    }



    // Replace fragment function
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_host, fragment)
        fragmentTransaction.commit()
    }
}