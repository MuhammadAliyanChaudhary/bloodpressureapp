package com.example.bloodpressure.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bloodpressure.R
import com.example.bloodpressure.adapters.WallpaperAdapter
import com.example.bloodpressure.databinding.FragmentMeditationBinding
import com.example.bloodpressure.databinding.FragmentWallPaperBinding
import com.example.bloodpressure.model.WallpaperModel
import com.example.bloodpressure.utils.WallpaperUtils


class MeditationFragment : Fragment() {

    private var binding: FragmentMeditationBinding?=null



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentMeditationBinding.inflate(inflater, container, false)



        binding!!.meditationRecyclerView.layoutManager = StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL)



        // This will pass the ArrayList to our Adapter
        val adapter = WallpaperAdapter(WallpaperUtils.getList(),  requireActivity())
        binding!!.meditationRecyclerView.adapter = adapter


        return binding!!.root
    }


}