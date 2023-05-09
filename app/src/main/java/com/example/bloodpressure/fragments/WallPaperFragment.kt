package com.example.bloodpressure.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bloodpressure.adapters.WallpaperAdapter
import com.example.bloodpressure.databinding.FragmentMeditationBinding
import com.example.bloodpressure.databinding.FragmentWallPaperBinding

import com.example.bloodpressure.model.WallpaperModel
import com.example.bloodpressure.utils.WallpaperUtils


class WallPaperFragment : Fragment() {
   private var binding: FragmentWallPaperBinding?=null
//    val wallpaperUrls = ArrayList<WallpaperModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentWallPaperBinding.inflate(inflater, container, false)


        binding!!.wallpaperRecyclerView.layoutManager = StaggeredGridLayoutManager( 2, StaggeredGridLayoutManager.VERTICAL)


        // This will pass the ArrayList to our Adapter
        val adapter = WallpaperAdapter(WallpaperUtils.getList(),  requireActivity())
        binding!!.wallpaperRecyclerView.adapter = adapter
        return binding!!.root


    }
}