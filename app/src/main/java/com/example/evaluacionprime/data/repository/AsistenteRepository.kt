package com.example.evaluacionprime.data.repository

import com.example.evaluacionprime.data.dao.AsistenteDao
import com.example.evaluacionprime.data.entity.Asistente
import kotlinx.coroutines.flow.Flow


class AsistenteRepository ( private val asistenteDao : AsistenteDao) {

    fun getAllAsistenteStream(): Flow<List<Asistente>>{
        return asistenteDao.getAllAsistentes()
    }

    fun getAsistentesByEventoId(eventoId: Int): Flow<List<Asistente>> {
        return asistenteDao.getAsistentesByEventoId(eventoId)
    }

    suspend fun insertAsistente(asistente: Asistente){
        asistenteDao.insertAsistente(asistente)
    }
}