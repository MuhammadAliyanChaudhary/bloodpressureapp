package com.example.bloodpressure.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bloodpressure.R
import com.example.bloodpressure.databinding.ActivityProfileDataBinding

import com.example.bloodpressure.utils.SharedPreferencesUser
import java.io.ByteArrayOutputStream

class ProfileDataActivity : AppCompatActivity() {
    var binding: ActivityProfileDataBinding?=null
    var sharedPreferencesUser: SharedPreferencesUser? = null
    val REQUEST_CODE = 100
    private var imageUri: Uri? = null
    var imageString : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileDataBinding.inflate(layoutInflater)
        var view = binding!!.root
        setContentView(view)


        // initialize sharedPreferences
        initSharedPreferences()

        getSharedPreferencesAndSetData()

        // click on save btn to save data
        binding!!.profileSaveBtn.setOnClickListener{
            if(!checkIsEmptyFields()){
                saveDataToSharedPreferences()
                sharedPreferencesUser!!.showProfileOrNot(true)
                startIntentToMainActivity()
            }
        }
        binding!!.editProfilePhoto.setOnClickListener{
            showAlertDialog()
        }







    }



    // init shared preferences
    private fun initSharedPreferences() {
        sharedPreferencesUser = SharedPreferencesUser(this)
        sharedPreferencesUser!!.initPreferences(this)
    }

    // Save data to shared preferences
    private fun saveDataToSharedPreferences(){

            sharedPreferencesUser!!.editPreferences(
                this
                ,binding!!.userNameET.text.toString()
                , binding!!.drNameET.text.toString()
                ,binding!!.userAgeET.text.toString()
                ,binding!!.userHeightET.text.toString()
                ,binding!!.userWeightET.text.toString()
                ,getGender()
                ,encode(imageUri!!)
            )




    }

    //Check if any field is empty
    private fun checkIsEmptyFields(): Boolean{
        var isEmptyField : Boolean = true
        if(binding!!.userNameET.text.toString().isEmpty()){
            binding!!.userNameET.error = "Enter Name"
        }
        else if(binding!!.drNameET.text.toString().isEmpty()){
            binding!!.drNameET.error = "Enter Dr Name"
        }
        else if(binding!!.ageTv.text.toString().isEmpty()){
            binding!!.ageTv.error = "Enter Your Age"
        }
        else if(binding!!.userHeightET.text.toString().isEmpty()){
            binding!!.userHeightET.error = "Enter Dr Name"
        }
        else if(binding!!.userWeightET.text.toString().isEmpty()){
            binding!!.userWeightET.error = "Enter Dr Name"

        }else if(getGender().isEmpty()|| getGender()== ""){
            Toast.makeText(applicationContext,"Please select your gender",Toast.LENGTH_SHORT).show()
        }
        else{
            isEmptyField = false
        }

        return isEmptyField

    }

    // get gender form radio buttons
    private fun getGender(): String{
        val id : Int = binding!!.radioBtGenderSelector.checkedRadioButtonId
        var genderText : String = ""

        when (id) {
            R.id.male_checked -> {
                genderText = binding!!.maleChecked.text.toString()
            }
            R.id.female_checked -> {
                genderText = binding!!.femaleChecked.text.toString()
            }
            R.id.other_checked -> {
                genderText = binding!!.otherChecked.text.toString()
            }
            else -> {
                Toast.makeText(applicationContext,"Please select your gender",Toast.LENGTH_SHORT).show()
            }
        }
        return genderText
    }

    // Start intent to Main Activity
    private fun startIntentToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun showAlertDialog(){
        val builder = AlertDialog.Builder(this)
        //set title for alert dialog
        builder.setTitle("Do you want to upload Image or Delete Image")
        //performing positive action
        builder.setPositiveButton("Upload Image"){dialogInterface, which ->
            Toast.makeText(applicationContext,"Upload Image",Toast.LENGTH_LONG).show()
            openGalleryForImage()
        }
        //performing cancel action
        builder.setNeutralButton("Cancel"){dialogInterface , which ->
        }
        //performing negative action
        builder.setNegativeButton("Delete Image"){dialogInterface, which ->
            Toast.makeText(applicationContext,"Delete Image",Toast.LENGTH_LONG).show()
            removeImageFromSharedPreferences()
        }
        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }
    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode ==REQUEST_CODE) {
            imageUri = data?.data
            binding!!.userProfileImage.setImageURI(imageUri) // handle chosen image

        }
    }

    fun encode(imageUri: Uri): String {
        if(imageUri==null){
            return ""
        }else{

            val input = contentResolver.openInputStream(imageUri)
            val image = BitmapFactory.decodeStream(input, null, null)

            // Encode image to base64 string
            val baos = ByteArrayOutputStream()
            image!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            var imageBytes = baos.toByteArray()
            val imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)
            return imageString

        }

    }


    fun decode(imageString: String) : Bitmap {
            // Decode base64 string to image
            val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
            val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

//        binding!!.retriveImage.setImageBitmap(decodedImage)

            return decodedImage
    }



    fun removeImageFromSharedPreferences(){
       var sharedpref =  sharedPreferencesUser!!.getPreferences(this)
       var imageString: String = sharedpref.getString(getString(R.string.imageUri), "").toString()
        if(imageString!=""|| imageString!=null){
            sharedPreferencesUser!!.editPreferencesImage(this, "")
        }
        binding!!.userProfileImage.setImageDrawable(getDrawable(R.drawable.profile_image))

    }

    fun getSharedPreferencesAndSetData(){
        var sharedpref =  sharedPreferencesUser!!.getPreferences(this)
        var imageString = sharedpref.getString(getString(R.string.imageUri),"")
        if(imageString!=null&& imageString!=""){
            binding!!.userProfileImage.setImageBitmap(decode(imageString))
        }
        binding!!.userNameET.setText(sharedpref.getString(getString(R.string.user_name),""))
        binding!!.drNameET.setText(sharedpref.getString(getString(R.string.user_dr_name),""))
        binding!!.userAgeET.setText(sharedpref.getString(getString(R.string.user_age),""))
        binding!!.userHeightET.setText(sharedpref.getString(getString(R.string.user_height),""))
        binding!!.userWeightET.setText(sharedpref.getString(getString(R.string.user_weight),""))
        var genderString = sharedpref.getString(getString(R.string.gender),"")
        Toast.makeText(applicationContext, "${sharedpref.getString(getString(R.string.gender),"")}", Toast.LENGTH_SHORT).show()
        if(genderString=="Male"){
            binding!!.maleChecked.isChecked = true
        }else if(genderString == "Female"){
            binding!!.femaleChecked.isChecked = true
        }else if(genderString == "Other")
        {
            binding!!.otherChecked.isChecked = true
        }
    }


}