package com.example.bloodpressure.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.bloodpressure.databinding.ActivitySplashBinding
import com.example.bloodpressure.utils.SharedPreferencesUser

class SplashActivity : AppCompatActivity() {
    var binding: ActivitySplashBinding? = null
    var sharedPreferencesUser: SharedPreferencesUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        var view = binding!!.root
        setContentView(view)


        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // initialize shared preference
        initSharedPreferences()

        // we used the postDelayed(Runnable, time) method
        // for a delayed time on splash.
        delayHandler()

        // show progress bar set progress value and animate
        setProgressValue(5)


    }


    // for delay on splash
    private fun delayHandler() {
        Handler(Looper.getMainLooper()).postDelayed({
            if (sharedPreferencesUser!!.isShownProfile(this)) {
                startIntentToBottomNavigationActivity()
            } else {
                startIntentToProfileActivity()
            }
        }, 2000) // 2000 is the delayed time in milliseconds.
    }


    // Start intent to Profile Activity
    private fun startIntentToProfileActivity() {
        val intent = Intent(this, ProfileDataActivity::class.java)
        startActivity(intent)
        finish()
    }


    // Start intent to BottomNavigation Activity
    private fun startIntentToBottomNavigationActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // for progressBar animation
    private fun setProgressValue(progress: Int) {
        // set the progress
        binding!!.progressBarSplash.setProgress(progress)
        // thread is used to change the progress value
        val thread = Thread {
            try {
                Thread.sleep(20)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            setProgressValue(progress + 1)
        }
        thread.start()
    }

    private fun initSharedPreferences() {
        sharedPreferencesUser = SharedPreferencesUser(this)
        sharedPreferencesUser!!.initPreferences(this)
    }


}
