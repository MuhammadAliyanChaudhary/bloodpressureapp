package com.example.bloodpressure.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="Patient")
data class Patient(

    @PrimaryKey(autoGenerate = true)
    var uid: Int
    ,var systolicBpNumber: Int
    ,var diastolicBpNumber: Int
    ,var pulseNumber: Int
    ,var note: String
    ,var date: String
    ,var time: String




)
