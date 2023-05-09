package com.example.bloodpressure.fragments

import android.R
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bloodpressure.activities.AddRecordActivity
import com.example.bloodpressure.activities.ProfileDataActivity
import com.example.bloodpressure.databinding.FragmentTrackerBinding
import com.example.bloodpressure.room.PatientDatabase
import com.example.bloodpressure.utils.SharedPreferencesUser
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate


class TrackerFragment : Fragment() {

    private var binding : FragmentTrackerBinding?=null
    var sharedPreferencesUser: SharedPreferencesUser? = null
    var database: PatientDatabase? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentTrackerBinding.inflate(inflater,container,false);

        binding!!.userImageTracFrag.setOnClickListener{
            replaceFragment(ProfileDetailFragment())
        }
        binding!!.addRecordBtn.setOnClickListener{

            startIntentToAddRecordActivity()
        }


        initSharedPreferences()
        getSharedPreferencesAndSetData()

        binding!!.chart1.setDrawBarShadow(false);
        binding!!.chart1.setDrawValueAboveBar(true);
        binding!!.chart1.description.isEnabled = false;
        binding!!.chart1.setMaxVisibleValueCount(6);
        binding!!.chart1.setPinchZoom(false);
        binding!!.chart1.setDrawGridBackground(false);



        setData()

        return binding!!.root
    }



    // Replace fragment function
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(com.example.bloodpressure.R.id.fragment_host, fragment).addToBackStack("TrackerFragment")
        fragmentTransaction.commit()
    }


   private fun setData(){

       val values: ArrayList<BarEntry> = ArrayList()
       values.add(BarEntry(12f , 120f))
       values.add(BarEntry(11f, 140f))
       values.add(BarEntry(10f, 160f))
       values.add(BarEntry(9f, 125f))
       values.add(BarEntry(8f, 135f))
       values.add(BarEntry(7f, 144f))



       val barDataSet = BarDataSet(values,"values")
       barDataSet.setColors(ColorTemplate.MATERIAL_COLORS, 255)
       barDataSet.valueTextColor = Color.BLACK

       val barData = BarData(barDataSet)

       binding!!.chart1.setFitBars(true)
       binding!!.chart1.data = barData


       binding!!.chart1.animateY(2000)
   }


    // Start intent to AddRecord Activity
    private fun startIntentToAddRecordActivity() {
        val intent = Intent(requireActivity(), AddRecordActivity::class.java)
        requireActivity().startActivity(intent)

    }



    fun getSharedPreferencesAndSetData(){
        var sharedpref =  sharedPreferencesUser!!.getPreferences(requireActivity())
        var imageString = sharedpref.getString(getString(com.example.bloodpressure.R.string.imageUri),"")
        if(imageString!=null&& imageString!=""){
            binding!!.userImageTracFrag.setImageBitmap(decode(imageString))
        }
        binding!!.userNameTracFrag.setText("Mr. "+sharedpref.getString(getString(com.example.bloodpressure.R.string.user_name),""))


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