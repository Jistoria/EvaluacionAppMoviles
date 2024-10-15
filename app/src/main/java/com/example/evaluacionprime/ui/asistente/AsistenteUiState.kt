package com.example.evaluacionprime.ui.asistente

import com.example.evaluacionprime.data.entity.Asistente

sealed class AsistenteUiState {
    object Loading : AsistenteUiState()
    data class Success(val asistentes: List<Asistente>) : AsistenteUiState()
    data class Error(val message: String) : AsistenteUiState()
}