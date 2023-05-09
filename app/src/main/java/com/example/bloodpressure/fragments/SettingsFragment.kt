package com.example.bloodpressure.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.bloodpressure.adapters.BpReadingsAdapter
import com.example.bloodpressure.databinding.FragmentHistoryBinding
import com.example.bloodpressure.databinding.FragmentSettingsBinding
import com.example.bloodpressure.room.PatientDatabase


class SettingsFragment : Fragment() {
    private var binding : FragmentSettingsBinding?=null
    var database: PatientDatabase? = null

    var pdfText : String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(inflater,container,false)


        database = Room.databaseBuilder(
            requireActivity(),
            PatientDatabase::class.java,
            "Patient"
        ).build()

//        binding!!.languageLayout.setOnClickListener{
//
//        }

        binding!!.exportLayout.setOnClickListener{
                getBpReadings()
        }
        binding!!.rateLayout.setOnClickListener{

        }
        binding!!.shareLayout.setOnClickListener{

        }
        binding!!.feedbackLayout.setOnClickListener{

        }
        binding!!.privacyLayout.setOnClickListener{

        }


        try {
            val pInfo = requireContext().packageManager.getPackageInfo(requireContext().packageName, 0)
            binding!!.versionTV.text  = "Version "+pInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }




        return binding!!.root
    }

    private fun getBpReadings(){
        database!!.patientDao().getBpReadings().observe(requireActivity()) {
            Log.d("TAG", "getRoomData: $it")

            // This will pass the ArrayList to our Adapter
//            val adapter = BpReadingsAdapter(it as ArrayList, requireActivity())

            for (a in 0..it.size-1){
//                holder.recipeIngredient.setText(holder.recipeIngredient.text.toString()+hits.recipe.ingredientLines[a]+"\n")
                pdfText = pdfText+ it.get(a)+"\n"
            }

            Log.d("Aliyan", "getBpReadings: $pdfText")

        }
    }


}