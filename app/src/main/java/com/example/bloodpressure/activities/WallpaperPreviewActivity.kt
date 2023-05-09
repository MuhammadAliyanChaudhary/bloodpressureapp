package com.example.bloodpressure.activities

import android.app.ProgressDialog
import android.app.WallpaperManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.bloodpressure.R
import com.example.bloodpressure.databinding.ActivityApplyWallpaperBinding
import com.example.bloodpressure.databinding.ActivityWallpaperPreviewBinding
import com.example.bloodpressure.utils.WallpaperUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.w3c.dom.Text
import java.io.IOException

class WallpaperPreviewActivity : AppCompatActivity() {
    var binding : ActivityWallpaperPreviewBinding?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWallpaperPreviewBinding.inflate(layoutInflater)
        var view = binding!!.root
        setContentView(view)


        var positionInt =  intent.getIntExtra("position", 1)

        Glide.with(this).load(WallpaperUtils.getList()[positionInt].image).into( binding!!.wallpaperPreview)

        binding!!.applyWallpaper.setOnClickListener{
            // on below line we are creating a new bottom sheet dialog.
            val dialog = BottomSheetDialog(this)

            // on below line we are inflating a layout file which we have created.
            val view = layoutInflater.inflate(R.layout.set_wallpaper_bottom_sheet, null)

            // on below line we are creating a variable for our button
            // which we are using to dismiss our dialog.
            val lockOnly = view.findViewById<TextView>(R.id.onlyLock)
            val homeOnly = view.findViewById<TextView>(R.id.onlyHome)
            val bothLockAndHome = view.findViewById<TextView>(R.id.both)

            lockOnly.setOnClickListener{

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    applyWallpaper(null, true, WallpaperManager.FLAG_LOCK, false)
                }
                dialog.dismiss()
            }

            homeOnly.setOnClickListener{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    applyWallpaper(null, false, WallpaperManager.FLAG_LOCK, true)
                }
                dialog.dismiss()
            }
            bothLockAndHome.setOnClickListener{
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    applyWallpaper(null, true, WallpaperManager.FLAG_LOCK, true)
                }
                dialog.dismiss()
            }
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//                applyWallpaper(null, true, WallpaperManager.FLAG_LOCK, true)
//            }



            // below line is use to set cancelable to avoid
            // closing of dialog box when clicking on the screen.
            dialog.setCancelable(true)

            // on below line we are setting
            // content view to our view.
            dialog.setContentView(view)

            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()
        }


    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun applyWallpaper(nothing: Nothing?, b: Boolean, flag: Int, b1: Boolean) {
        val progressDialog = ProgressDialog(this@WallpaperPreviewActivity)
        progressDialog.setTitle("Please Wait")
        progressDialog.setMessage("Applying Wallpaper")
        progressDialog.show()
        binding!!.wallpaperPreview.buildDrawingCache()
        val bm: Bitmap = binding!!.wallpaperPreview.drawingCache
        CoroutineScope(Dispatchers.IO).launch {
//            val bm = (PreviewActivityBinding!!.imageWallpaper.drawable as BitmapDrawable).bitmap

            // TODO Auto-generated method stub
            val myWallpaperManager = WallpaperManager.getInstance(applicationContext)
            try {
                val display: Display = windowManager.defaultDisplay
                val size = Point()
                display.getSize(size)
                val fullWidth = size.x
                val fullHeight = size.y

                val bitmapResized = Bitmap.createScaledBitmap(bm, fullWidth, fullHeight, true)
                myWallpaperManager.suggestDesiredDimensions(
                    bitmapResized.width,
                    bitmapResized.height
                )
                if (!b1) {
                    myWallpaperManager.setBitmap(bitmapResized, nothing, b, flag)
                } else {
                    myWallpaperManager.setBitmap(
                        bitmapResized,
                        nothing,
                        b,
                        WallpaperManager.FLAG_SYSTEM
                    )
                    myWallpaperManager.setBitmap(
                        bitmapResized,
                        nothing,
                        b,
                        WallpaperManager.FLAG_LOCK
                    )
                }
                myWallpaperManager.setBitmap(bitmapResized, nothing, b, flag)
                withContext(Dispatchers.Main) {
                    progressDialog.hide()
                    Toast.makeText(
                        this@WallpaperPreviewActivity,
                        "wallpaper apply successfuly",
                        Toast.LENGTH_SHORT
                    ).show()
                    onBackPressed()
                }

            } catch (e: IOException) {
                // TODO Auto-generated catch block
                progressDialog.hide()
                e.printStackTrace()

            }
        }
    }



}