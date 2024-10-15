package com.example.evaluacionprime.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.evaluacionprime.data.dao.AsistenteDao
import com.example.evaluacionprime.data.dao.EventoDao
import com.example.evaluacionprime.data.entity.Asistente
import com.example.evaluacionprime.data.entity.Evento

@Database(entities = [Evento::class, Asistente::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun asistenteDao(): AsistenteDao
    abstract fun eventoDao() : EventoDao

    companion object{
        @Volatile
        private var Instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, AppDatabase::class.java, "tinder_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}