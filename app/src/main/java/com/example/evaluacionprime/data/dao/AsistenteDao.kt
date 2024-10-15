package com.example.evaluacionprime.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.evaluacionprime.data.entity.Asistente
import kotlinx.coroutines.flow.Flow



@Dao
interface AsistenteDao {
    @Query("SELECT * FROM asistente")
    fun getAllAsistentes () : Flow<List<Asistente>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsistente(asistente: Asistente)

    @Query("SELECT * FROM asistente WHERE eventoId = :eventoId")
    fun getAsistentesByEventoId(eventoId:Int) : Flow<List<Asistente>>
}