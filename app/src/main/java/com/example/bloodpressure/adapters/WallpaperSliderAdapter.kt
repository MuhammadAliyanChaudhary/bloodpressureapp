package com.example.bloodpressure.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bloodpressure.R
import com.example.bloodpressure.interfaces.DownloadClick
import com.example.bloodpressure.model.WallpaperModel

class WallpaperSliderAdapter(var mList: ArrayList<WallpaperModel>, val context: Context, val itemOnClick: DownloadClick) :
    RecyclerView.Adapter<WallpaperSliderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wallpaper_slider, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wallpaperUrls = mList[position]

        if(context!=null){
            Glide.with(context).load(wallpaperUrls.image).into(holder.wallpaperImageSlider)
        }

        holder.downloadImage.setOnClickListener{
            itemOnClick.OnDownloadClick(wallpaperUrls.image)
        }


    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val wallpaperImageSlider: ImageView = itemView.findViewById(R.id.wallpaperImageSlider)
        val downloadImage: ImageView = itemView.findViewById(R.id.downloadImage)
    }
}