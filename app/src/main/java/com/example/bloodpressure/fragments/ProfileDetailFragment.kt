package com.example.bloodpressure.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.room.Room
import com.example.bloodpressure.R
import com.example.bloodpressure.activities.FaqActivity
import com.example.bloodpressure.activities.MainActivity
import com.example.bloodpressure.activities.ProfileDataActivity
import com.example.bloodpressure.activities.WallPaperActivity
import com.example.bloodpressure.adapters.BpReadingsAdapter
import com.example.bloodpressure.databinding.FragmentProfileDetailBinding
import com.example.bloodpressure.room.PatientDatabase
import com.example.bloodpressure.utils.SharedPreferencesUser


class ProfileDetailFragment : Fragment() {


    var binding: FragmentProfileDetailBinding?=null
    var sharedPreferencesUser: SharedPreferencesUser? = null
    var database: PatientDatabase? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileDetailBinding.inflate(inflater, container,false)

        initSharedPreferences()
        getSharedPreferencesAndSetData()
        database = Room.databaseBuilder(
            requireActivity(),
            PatientDatabase::class.java,
            "Patient"
        ).build()

        getBpReadings()

        binding!!.backArrow.setOnClickListener{
            replaceFragment(TrackerFragment())
        }
        binding!!.editBtnProfile.setOnClickListener{
            startIntentToProfileDataActivity()
        }

        binding!!.wallpaperBannerBtn.setOnClickListener{
            startIntentToWallpaperActivity()
        }

        binding!!.faqBannerBtn.setOnClickListener{
            startIntentToFaqActivity()
        }






        return binding!!.root
    }

    private fun getBpReadings(){
        database!!.patientDao().getBpReadings().observe(requireActivity()) {
            Log.d("TAG", "getRoomData: $it")

            // This will pass the ArrayList to our Adapter
            val adapter = BpReadingsAdapter(it as ArrayList, requireActivity())
            var systolicNum : Int = it.get(0).systolicBpNumber
           binding!!.systolicReadingProfile.text = it.get(0).systolicBpNumber.toString()
           binding!!.diastolicReadingProfile.text = it.get(0).diastolicBpNumber.toString()
           binding!!.bpmReadingProfile.text = it.get(0).pulseNumber.toString()


            if(systolicNum<110){

                binding!!.bPType.text = "Low Blood Pressure"
            }else if(systolicNum>=110 && systolicNum<=120){

                binding!!.bPType.text  = "Normal"
            }
            else if(systolicNum>120 && systolicNum<=130){
                binding!!.bPType.text  = "Hypertension"
            }else if(systolicNum>130 && systolicNum<=140){
                binding!!.bPType.text  = "Hypertension(S1)"
            }else if(systolicNum>140 && systolicNum<=160){
                binding!!.bPType.text  = "Hypertension(S2)"
            }else if(systolicNum>160 && systolicNum<=300){
                binding!!.bPType.text  = "Hypertension(S3)"
            }else{
                binding!!.bPType.text  = "Normal"
            }

        }
    }


    // Replace fragment function
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_host, fragment)
        fragmentTransaction.commit()
    }
    // Start intent to BottomNavigation Activity
    private fun startIntentToProfileDataActivity() {
        val intent = Intent(requireActivity(), ProfileDataActivity::class.java)
        requireActivity().startActivity(intent)

    }

    // Start intent to Wallpaper Activity
    private fun startIntentToWallpaperActivity() {
        val intent = Intent(requireActivity(), WallPaperActivity::class.java)
        requireActivity().startActivity(intent)

    }

    // Start intent to Faq Activity
    private fun startIntentToFaqActivity() {
        val intent = Intent(requireActivity(), FaqActivity::class.java)
        requireActivity().startActivity(intent)

    }


    fun getSharedPreferencesAndSetData(){
        var sharedpref =  sharedPreferencesUser!!.getPreferences(requireActivity())
        var imageString = sharedpref.getString(getString(R.string.imageUri),"")
        if(imageString!=null&& imageString!=""){
            binding!!.userProfileImage.setImageBitmap(decode(imageString))
        }
        binding!!.nameProfileUser.setText("Mr. "+sharedpref.getString(getString(R.string.user_name),""))
        binding!!.patientOfProfile.setText("Patient of: "+sharedpref.getString(getString(R.string.user_dr_name),""))

    }

    // init shared preferences
    private fun initSharedPreferences() {
        sharedPreferencesUser = SharedPreferencesUser(requireActivity())
        sharedPreferencesUser!!.initPreferences(requireActivity())
    }
    fun decode(imageString: String) : Bitmap {
        // Decode base64 string to image
        val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

//        binding!!.retriveImage.setImageBitmap(decodedImage)

        return decodedImage
    }
}