package com.example.evaluacionprime.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("asistente")
data class Asistente (
    @PrimaryKey val id : Int = 0,
    val nombre : String,
    val apellido : String,
    val edad : Int,
    val genero : Genero ?= null,
    val eventoId : Int
)