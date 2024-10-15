package com.example.evaluacionprime.data.repository
import com.example.evaluacionprime.data.dao.EventoDao
import com.example.evaluacionprime.data.entity.Asistente
import com.example.evaluacionprime.data.entity.Evento
import kotlinx.coroutines.flow.Flow

class EventoRepository( private val eventoDao : EventoDao) {

    fun getAllEventoStream(): Flow<List<Evento>> {
        return eventoDao.getAllEvento()
    }


    suspend fun insertEvento(evento: Evento){
        eventoDao.insertEvento(evento)
    }
}