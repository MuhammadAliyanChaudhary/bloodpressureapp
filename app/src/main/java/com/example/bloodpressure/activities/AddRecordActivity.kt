package com.example.bloodpressure.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.bloodpressure.R
import com.example.bloodpressure.databinding.ActivityAddRecordBinding
import com.example.bloodpressure.room.Patient
import com.example.bloodpressure.room.PatientDatabase
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shawnlin.numberpicker.NumberPicker
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class AddRecordActivity : AppCompatActivity() {
    var binding: ActivityAddRecordBinding?=null
    var systolicNum : Int = 120
    var diastolicNum : Int = 80
    var pulseNum : Int = 72
    var noteAdded : kotlin.String = "No Note Added"
    var database: PatientDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecordBinding.inflate(layoutInflater)
        var view = binding!!.root
        setContentView(view)



        database = Room.databaseBuilder(
            applicationContext,
            PatientDatabase::class.java,
            "Patient"
        ).build()


        // OnValueChangeListener
        // OnValueChangeListener
        // OnValueChangeListener
        // OnValueChangeListener
        binding!!.systolicNumberPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            systolicNum = newVal
            changeBarColors()

        })

        binding!!.diastolicNumberPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            diastolicNum = newVal
        })

        binding!!.pulseNumberPicker.setOnValueChangedListener(NumberPicker.OnValueChangeListener { picker, oldVal, newVal ->
            pulseNum = newVal
        })


        binding!!.addNoteBtn.setOnClickListener{


            // on below line we are creating a new bottom sheet dialog.
            val dialog = BottomSheetDialog(this)

            // on below line we are inflating a layout file which we have created.
            val view = layoutInflater.inflate(R.layout.bottom_sheet_note, null)

            // on below line we are creating a variable for our button
            // which we are using to dismiss our dialog.
            val btnClose = view.findViewById<ImageView>(R.id.btn_close)
            val customNoteEdit = view.findViewById<EditText>(R.id.customNoteET)
            val afterWalking = view.findViewById<Button>(R.id.afterWalking)
            val saveBtn = view.findViewById<Button>(R.id.saveBtn)
            val afterMedication = view.findViewById<Button>(R.id.afterMedication)
            val beforeMeal = view.findViewById<Button>(R.id.beforeMeal)
            val sitting = view.findViewById<Button>(R.id.sitting)
            val laying = view.findViewById<Button>(R.id.laying)


            afterWalking.setOnClickListener{

                customNoteEdit.setText("After Walking")

            }

            afterMedication.setOnClickListener{
                customNoteEdit.setText("After Medication")
            }

            beforeMeal.setOnClickListener{
                customNoteEdit.setText("Before Meal")
            }

            sitting.setOnClickListener{
                customNoteEdit.setText("Sitting")
            }

            laying.setOnClickListener{
                customNoteEdit.setText("Laying")
            }


            saveBtn.setOnClickListener{
                if(customNoteEdit.text.toString()!=""|| customNoteEdit.text.toString()!="No Note Added"){
                    noteAdded = customNoteEdit.text.toString()

                }

                Toast.makeText(applicationContext, ""+noteAdded, Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            }

            // on below line we are adding on click listener
            // for our dismissing the dialog button.
            btnClose.setOnClickListener {
                // on below line we are calling a dismiss
                // method to close our dialog.
                dialog.dismiss()
            }
            // below line is use to set cancelable to avoid
            // closing of dialog box when clicking on the screen.
            dialog.setCancelable(false)

            // on below line we are setting
            // content view to our view.
            dialog.setContentView(view)

            // on below line we are calling
            // a show method to display a dialog.
            dialog.show()


        }


        binding!!.saveRecordBtn.setOnClickListener{

            GlobalScope.launch {
                database!!.patientDao().insertPatient(
                    Patient(
                        0,
                        systolicNum,
                        diastolicNum,
                        pulseNum,
                        noteAdded,
                        getDate().toString(),
                        getTime().toString()

                    )
                )
            }

            Toast.makeText(applicationContext, "Record Added", Toast.LENGTH_SHORT).show()
            startIntentToBottomNavActivity()
        }






    }


    private fun changeBarColors(){
        if((systolicNum<=100 && systolicNum>=20) && (diastolicNum<=100 && diastolicNum>=20)&& (pulseNum<=200 && pulseNum>=20)){
            binding!!.blueCard.strokeColor = getColor(R.color.black)
            binding!!.normalCard.strokeColor = getColor(R.color.transparent)
            binding!!.yellowCard.strokeColor = getColor(R.color.transparent)
            binding!!.modrateCard.strokeColor = getColor(R.color.transparent)
            binding!!.orangeCard.strokeColor = getColor(R.color.transparent)
            binding!!.redCard.strokeColor = getColor(R.color.transparent)
        }else if((systolicNum<=120 && systolicNum>=101) && (diastolicNum<=100 && diastolicNum>=60)&& (pulseNum<=200 && pulseNum>=50)){

            binding!!.blueCard.strokeColor = getColor(R.color.transparent)
            binding!!.normalCard.strokeColor = getColor(R.color.black)
            binding!!.yellowCard.strokeColor = getColor(R.color.transparent)
            binding!!.modrateCard.strokeColor = getColor(R.color.transparent)
            binding!!.orangeCard.strokeColor = getColor(R.color.transparent)
            binding!!.redCard.strokeColor = getColor(R.color.transparent)
        }
        else if((systolicNum<=140 && systolicNum>=121) && (diastolicNum<=300)&& (pulseNum<=200)){

            binding!!.blueCard.strokeColor = getColor(R.color.transparent)
            binding!!.normalCard.strokeColor = getColor(R.color.transparent)
            binding!!.yellowCard.strokeColor = getColor(R.color.transparent)
            binding!!.modrateCard.strokeColor = getColor(R.color.black)
            binding!!.orangeCard.strokeColor = getColor(R.color.transparent)
            binding!!.redCard.strokeColor = getColor(R.color.transparent)
        }
        else if((systolicNum<=160 && systolicNum>=141) && (diastolicNum<=300)&& (pulseNum<=200)){

            binding!!.blueCard.strokeColor = getColor(R.color.transparent)
            binding!!.normalCard.strokeColor = getColor(R.color.transparent)
            binding!!.yellowCard.strokeColor = getColor(R.color.black)
            binding!!.modrateCard.strokeColor = getColor(R.color.transparent)
            binding!!.orangeCard.strokeColor = getColor(R.color.transparent)
            binding!!.redCard.strokeColor = getColor(R.color.transparent)
        }
        else if((systolicNum<=180 && systolicNum>=161) && (diastolicNum<=300)&& (pulseNum<=200)){

            binding!!.blueCard.strokeColor = getColor(R.color.transparent)
            binding!!.normalCard.strokeColor = getColor(R.color.transparent)
            binding!!.yellowCard.strokeColor = getColor(R.color.transparent)
            binding!!.modrateCard.strokeColor = getColor(R.color.transparent)
            binding!!.orangeCard.strokeColor = getColor(R.color.black)
            binding!!.redCard.strokeColor = getColor(R.color.transparent)
        }
        else if((systolicNum<=300 && systolicNum>=181) && (diastolicNum<=300)&& (pulseNum<=200)){

            binding!!.blueCard.strokeColor = getColor(R.color.transparent)
            binding!!.normalCard.strokeColor = getColor(R.color.transparent)
            binding!!.yellowCard.strokeColor = getColor(R.color.transparent)
            binding!!.modrateCard.strokeColor = getColor(R.color.transparent)
            binding!!.orangeCard.strokeColor = getColor(R.color.transparent)
            binding!!.redCard.strokeColor = getColor(R.color.black)
        }

    }


    fun getDate(): kotlin.String? {
        val s: kotlin.String
        s = if (binding!!.datePicker.getMonth() < 10 && binding!!.datePicker.getDayOfMonth() < 10) {
            binding!!.datePicker.getYear()
                .toString() + "-" + "0" + binding!!.datePicker.getMonth() + "-" + "0" + binding!!.datePicker.getDayOfMonth()
        } else if (binding!!.datePicker.getMonth() < 10) {
            binding!!.datePicker.getYear()
                .toString() + "-" + "0" + binding!!.datePicker.getMonth() + "-" + binding!!.datePicker.getDayOfMonth()
        } else {
            binding!!.datePicker.getYear()
                .toString() + "-" + binding!!.datePicker.getMonth() + "-" + "0" + binding!!.datePicker.getDayOfMonth()
        }
        return s
    }


    fun getTime(): String? {
        var hour: Int
        val minute: Int
        val am_pm: String
        if (Build.VERSION.SDK_INT >= 23) {
            hour = binding!!.timePicker.getHour()
            minute = binding!!.timePicker.getMinute()
        } else {
            hour =binding!!.timePicker.getCurrentHour()
            minute = binding!!.timePicker.getCurrentMinute()
        }
        if (hour > 12) {
            am_pm = "PM"
            hour = hour - 12
        } else {
            am_pm = "AM"
        }
        return "$hour:$minute $am_pm"
    }


    private fun startIntentToBottomNavActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}




