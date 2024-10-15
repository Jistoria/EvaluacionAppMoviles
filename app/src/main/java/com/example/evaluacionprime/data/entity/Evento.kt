package com.example.evaluacionprime.data.entity
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("evento")
data class Evento (
    @PrimaryKey val id : Int = 0,
    val nombre : String,
    val image : String,
    val horas : Int
)