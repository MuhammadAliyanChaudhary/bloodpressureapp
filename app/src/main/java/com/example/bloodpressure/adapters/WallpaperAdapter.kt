package com.example.bloodpressure.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bloodpressure.R
import com.example.bloodpressure.activities.ApplyWallpaperActivity
import com.example.bloodpressure.activities.MainActivity
import com.example.bloodpressure.model.WallpaperModel
import com.example.bloodpressure.room.Patient

class WallpaperAdapter(var mList: ArrayList<WallpaperModel>, val context: Context) :
    RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wallpaper, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wallpaperUrls = mList[position]
        Glide.with(context).load(wallpaperUrls.image).into(holder.wallpaperImage)
        holder.wallPaperCard.setOnClickListener{
          startIntentToApplyWallpaperActivity(position)
        }


    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val wallpaperImage: ImageView = itemView.findViewById(R.id.wallpaperImage)
        val wallPaperCard: CardView = itemView.findViewById(R.id.wallPaperCard)
    }

    private fun startIntentToApplyWallpaperActivity(position : Int) {
        val intent = Intent(context, ApplyWallpaperActivity::class.java)
        intent.putExtra("position",position)
        context.startActivity(intent)
    }
}