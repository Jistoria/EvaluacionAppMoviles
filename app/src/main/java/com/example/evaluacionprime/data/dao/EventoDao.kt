package com.example.evaluacionprime.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.evaluacionprime.data.entity.Evento
import kotlinx.coroutines.flow.Flow



@Dao
interface EventoDao {
    @Query("SELECT * FROM evento")
    fun getAllEvento () : Flow<List<Evento>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvento(evento: Evento)
}