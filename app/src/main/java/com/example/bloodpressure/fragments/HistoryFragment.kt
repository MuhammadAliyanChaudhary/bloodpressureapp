package com.example.bloodpressure.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.bloodpressure.R
import com.example.bloodpressure.adapters.BpReadingsAdapter
import com.example.bloodpressure.databinding.FragmentHistoryBinding

import com.example.bloodpressure.room.PatientDatabase


class HistoryFragment : Fragment() {

    private var binding : FragmentHistoryBinding?=null
    var database: PatientDatabase? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater,container,false);

        binding!!.bpReadingsRecyclerView!!.layoutManager = LinearLayoutManager(requireActivity())

        database = Room.databaseBuilder(
            requireActivity(),
            PatientDatabase::class.java,
            "Patient"
        ).build()

        getBpReadings()



        return binding!!.root
    }

    private fun getBpReadings(){
        database!!.patientDao().getBpReadings().observe(requireActivity()) {
            Log.d("TAG", "getRoomData: $it")

            // This will pass the ArrayList to our Adapter
            val adapter = BpReadingsAdapter(it as ArrayList, requireActivity())
            binding!!.bpReadingsRecyclerView.adapter = adapter

        }
    }


}