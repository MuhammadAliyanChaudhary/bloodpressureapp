package com.example.bloodpressure.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.bloodpressure.R
import com.example.bloodpressure.room.Patient
import com.example.bloodpressure.room.PatientDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class BpReadingsAdapter(var mList: ArrayList<Patient>, val context: Context)  :
    RecyclerView.Adapter<BpReadingsAdapter.ViewHolder>() {
    var database: PatientDatabase? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bp_reading, parent, false)

        return BpReadingsAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patientData = mList[position]


        holder.systolicReading.text = patientData.systolicBpNumber.toString()
        holder.diastolicReading.text = patientData.diastolicBpNumber.toString()
        holder.pulseDisplay.text = patientData.pulseNumber.toString()+"-BPM"
        holder.timeDisplay.text = patientData.time+""
        holder.dateDisplay.text = patientData.date.drop(5)+", "

        if(patientData.systolicBpNumber<110){
            holder.indicationImage.setImageResource(R.drawable.elipse_blue)
            holder.bpState.text = "Low"
        }else if(patientData.systolicBpNumber>=110 && patientData.systolicBpNumber<=120){
            holder.indicationImage.setImageResource(R.drawable.elipes_norma)
            holder.bpState.text = "Normal"
        }
        else if(patientData.systolicBpNumber>120 && patientData.systolicBpNumber<=130){
            holder.bpState.text = "Hypertension"
            holder.indicationImage.setImageResource(R.drawable.elipse_yellow_greeen)
        }else if(patientData.systolicBpNumber>130 && patientData.systolicBpNumber<=140){
            holder.bpState.text = "Hypertension(S1)"
            holder.indicationImage.setImageResource(R.drawable.elipse_yellow)
        }else if(patientData.systolicBpNumber>140 && patientData.systolicBpNumber<=160){
            holder.bpState.text = "Hypertension(S2)"
            holder.indicationImage.setImageResource(R.drawable.elipse_orange)
        }else if(patientData.systolicBpNumber>160 && patientData.systolicBpNumber<=300){
            holder.bpState.text = "Hypertension(S3)"
            holder.indicationImage.setImageResource(R.drawable.elipse_red)
        }else{
            holder.indicationImage.setImageResource(R.drawable.elipse_blue)
            holder.bpState.text = "Normal"
        }


        holder.delete_btn_img.setOnClickListener{
            database = Room.databaseBuilder(
                context,
                PatientDatabase::class.java,
                "Patient"
            ).build()

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete?")
            builder.setMessage("Do you want to delete this record?")
//builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton("Yes") { dialog, which ->
                GlobalScope.launch {
                    database!!.patientDao().deletePatient(patientData)

                }
                mList.remove(patientData)
                notifyDataSetChanged()
            }

            builder.setNegativeButton("No") { dialog, which ->
            }
            builder.show()


        }



    }

    override fun getItemCount(): Int {

       return mList.size
    }



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {


        val systolicReading: TextView = itemView.findViewById(R.id.systolicReading)
        val diastolicReading: TextView = itemView.findViewById(R.id.diastolicReading)
        val bpState: TextView = itemView.findViewById(R.id.bpState)
        val pulseDisplay: TextView = itemView.findViewById(R.id.pulseDisplay)
        val dateDisplay: TextView = itemView.findViewById(R.id.dateDisplay)
        val timeDisplay: TextView = itemView.findViewById(R.id.timeDisplay)
        val delete_btn_img: ImageView = itemView.findViewById(R.id.delete_btn_img)
        val indicationImage: ImageView = itemView.findViewById(R.id.indicationImage)

    }
}


