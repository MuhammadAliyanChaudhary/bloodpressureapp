package com.example.bloodpressure.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PatientDao {

    @Insert
    suspend fun insertPatient(patient: Patient)

    @Update
    suspend fun updatePatient(patient: Patient)

    @Delete
    suspend fun deletePatient(patient: Patient)


    @Query("SELECT * FROM patient  ORDER BY uid DESC")
    fun getBpReadings() : LiveData<List<Patient>>
}