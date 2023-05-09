package com.example.bloodpressure.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.bloodpressure.R

class SharedPreferencesUser(context: Context) {

    var context : Context = context
    var userName : String = ""
    var drName : String = ""
    var userAge : Int = 0
    var userHeight : Double = 0.0
    var userWeight : Double = 0.0
    var userGender : String = ""
    var USER_PREFERENCES : String = "UserPrefs"
    var TOKEN : String = "Token"



//    , userName: String, drName : String, userAge: Int, userHeight: Double, userWeight: Double,
//    userGender:String


     fun initPreferences(context: Context) {

       context?.getSharedPreferences(
            context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)

    }

    fun editPreferences(context: Context,userName: String, drName : String, userAge: String, userHeight: String, userWeight: String,
                               userGender:String, imageString: String){
        val sharedPref = context?.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(context.getString(R.string.user_name), userName)
            putString(context.getString(R.string.user_dr_name), drName)
            putString(context.getString(R.string.user_age), userAge)
            putString(context.getString(R.string.user_height), userHeight)
            putString(context.getString(R.string.user_weight), userWeight)
            putString(context.getString(R.string.user_gender), userGender)
            putString(context.getString(R.string.imageUri), imageString)
            apply()
        }
    }



    fun editPreferencesImage(context: Context, imageString: String){
        val sharedPref = context?.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(context.getString(R.string.imageUri), imageString)
            apply()
        }
    }


     fun getPreferences(context: Context): SharedPreferences{

        val sharedPref: SharedPreferences ?= context?.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
        return sharedPref!!
    }

    fun clearSharedPreferences(context: Context){
        val editor: SharedPreferences.Editor = context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE).edit()
        editor.clear()
        editor.commit()
    }


    fun showProfileOrNot(isShown : Boolean) {

        val sharedPref = context?.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putBoolean("shownProfileOrNot", isShown)
            apply()
        }

    }

    fun isShownProfile(context: Context): Boolean {
        return context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE).getBoolean("shownProfileOrNot", false)
    }




}




