package com.example.bloodpressure.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.bloodpressure.fragments.MeditationFragment
import com.example.bloodpressure.fragments.WallPaperFragment

class PageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2;
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return WallPaperFragment()
            }
            1 -> {
                return MeditationFragment()
            }
            else -> {
                return WallPaperFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Wallpapers"
            }
            1 -> {
                return "Meditation"
            }
        }
        return super.getPageTitle(position)
    }
}