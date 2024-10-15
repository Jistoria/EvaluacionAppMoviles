package com.example.evaluacionprime.provider

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.evaluacionprime.ApplicationEvent
import com.example.evaluacionprime.ui.asistente.AsistenteViewModel
import com.example.evaluacionprime.ui.evento.EventoViewModel


object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            val eventoRepository = applicationEvent().eventoRepository
            EventoViewModel(eventoRepository)
        }
    }

    val AsistenteFactory = viewModelFactory {
        initializer {
            val asistenteRepository = applicationEvent().asistenteRepository
            AsistenteViewModel(asistenteRepository)
        }
    }
}

fun CreationExtras.applicationEvent(): ApplicationEvent  =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as ApplicationEvent)