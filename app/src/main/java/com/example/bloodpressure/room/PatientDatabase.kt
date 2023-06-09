package com.example.bloodpressure.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Patient::class] ,  version = 1)
abstract class PatientDatabase : RoomDatabase() {

    abstract fun patientDao() : PatientDao


}