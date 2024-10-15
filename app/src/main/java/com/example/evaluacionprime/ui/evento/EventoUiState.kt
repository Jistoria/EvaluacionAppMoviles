package com.example.evaluacionprime.ui.evento

import com.example.evaluacionprime.data.entity.Evento

sealed class EventoUiState {
    object Loading : EventoUiState()
    data class Success(val eventos: List<Evento>) : EventoUiState()
    data class Error(val message: String) : EventoUiState()
}
