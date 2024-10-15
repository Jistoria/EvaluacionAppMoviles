package com.example.evaluacionprime

import android.app.Application
import com.example.evaluacionprime.data.database.AppDatabase
import com.example.evaluacionprime.data.entity.Asistente
import com.example.evaluacionprime.data.entity.Evento
import com.example.evaluacionprime.data.entity.Genero
import com.example.evaluacionprime.data.repository.AsistenteRepository
import com.example.evaluacionprime.data.repository.EventoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ApplicationEvent : Application () {
    val database by lazy {AppDatabase.getDatabase((this))}
    val eventoRepository by lazy { EventoRepository(database.eventoDao())}
    val asistenteRepository by lazy { AsistenteRepository(database.asistenteDao()) }

    override fun onCreate() {
        super.onCreate()
        insertInitialData()
    }

    private fun insertInitialData() {
        CoroutineScope(Dispatchers.IO).launch {
            val initialEventos = listOf(
                Evento(id = 1, nombre = "Evento A", image = "imagen_a.jpg", horas = 2),
                Evento(id = 2, nombre = "Evento B", image = "imagen_b.jpg", horas = 4),
                Evento(id = 3, nombre = "Evento C", image = "imagen_c.jpg", horas = 6)
            )

            val currentEventos = eventoRepository.getAllEventoStream().first()

            if (currentEventos.isEmpty()) {
                initialEventos.forEach { evento ->
                    eventoRepository.insertEvento(evento)

                    val asistentes = listOf(
                        Asistente(nombre = "Asistente ${evento.id}", apellido = "Apellido ${evento.id}", edad = 25, genero = Genero.MALE, eventoId = evento.id),
                        Asistente(nombre = "Asistente ${evento.id}", apellido = "Apellido ${evento.id}", edad = 30, genero = Genero.FEMALE, eventoId = evento.id)
                    )

                    asistentes.forEach { asistente ->
                        asistenteRepository.insertAsistente(asistente)
                    }
                }
            }
        }
    }
}