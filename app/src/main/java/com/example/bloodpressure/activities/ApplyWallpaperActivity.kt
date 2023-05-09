package com.example.bloodpressure.activities

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.bloodpressure.adapters.WallpaperSliderAdapter
import com.example.bloodpressure.databinding.ActivityApplyWallpaperBinding
import com.example.bloodpressure.interfaces.DownloadClick
import com.example.bloodpressure.utils.WallpaperUtils
import java.io.File


class ApplyWallpaperActivity : AppCompatActivity(), DownloadClick {
    var binding: ActivityApplyWallpaperBinding? = null
    var permissionGranted : Boolean = false

    private val requestPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            // do something
            permissionGranted = isGranted

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApplyWallpaperBinding.inflate(layoutInflater)
        var view = binding!!.root
        setContentView(view)

        requestPermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)





        binding!!.carouselRecyclerview.getCarouselLayoutManager()
        // This will pass the ArrayList to our Adapter
        val adapter = WallpaperSliderAdapter(WallpaperUtils.getList(), this, this)
        binding!!.carouselRecyclerview.adapter = adapter
        binding!!.carouselRecyclerview.apply {
            set3DItem(true)
            setAlpha(true)
            setInfinite(true)
        }
        var positionInt = intent.getIntExtra("position", 1)
        binding!!.carouselRecyclerview.scrollToPosition(positionInt)


        binding!!.setWallpaper.setOnClickListener {
            var positionNum = binding!!.carouselRecyclerview.getSelectedPosition()
            Log.d("Aliyan", "onCreate: ${binding!!.carouselRecyclerview.getSelectedPosition()}")
            Log.d("Aliyan", "onCreate: ${WallpaperUtils.getList()[positionNum].image}")
            startIntentToPreviewWallpaperActivity(binding!!.carouselRecyclerview.getSelectedPosition())
        }


        binding!!.backArrow.setOnClickListener {
            onBackPressed()
        }


    }

    // Start intent to Preview Wallpaper Activity
    private fun startIntentToPreviewWallpaperActivity(positionInt: Int) {
        val intent = Intent(this, WallpaperPreviewActivity::class.java)
        intent.putExtra("position", positionInt)
        startActivity(intent)

    }

    fun downloadImage(baseActivity: Context, url: String?, title: String?): Long {
        val direct =
            File(Environment.getExternalStorageDirectory().toString() + "/BloodPressure")

        if (!direct.exists()) {
            direct.mkdirs()
        }
//          val extension = url?.substring(url.lastIndexOf("."))
        val extension = ".jpg"
        val downloadReference: Long
        var dm: DownloadManager
        dm = baseActivity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
            request.setDestinationInExternalFilesDir(
                this, Environment.DIRECTORY_DOWNLOADS, "Wallpaper" + System.currentTimeMillis() + ".jpg"
            )

        }else{

            request.setDestinationInExternalPublicDir(
               "$direct/" , "Wallpapers" + System.currentTimeMillis() + extension
            )

        }

        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setTitle(title)
        Toast.makeText(baseActivity, "start Downloading..", Toast.LENGTH_SHORT).show()

        downloadReference = dm.enqueue(request)

        return downloadReference

    }

    override fun OnDownloadClick(item: String) {
        checkPermission(item)

    }

    fun checkPermission(url: String) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
        ) {
            // Pass any permission you want while launching
            requestPermission.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }else{
            downloadImage(this@ApplyWallpaperActivity, url, "Wallpaper")
        }

    }

}