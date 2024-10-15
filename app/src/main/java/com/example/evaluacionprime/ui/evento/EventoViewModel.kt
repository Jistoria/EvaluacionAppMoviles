package com.example.evaluacionprime.ui.evento

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evaluacionprime.data.dao.EventoDao
import com.example.evaluacionprime.data.entity.Evento
import com.example.evaluacionprime.data.repository.EventoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class EventoViewModel(private val eventoRepository: EventoRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<EventoUiState>(EventoUiState.Loading)
    val uiState: StateFlow<EventoUiState> = _uiState.asStateFlow()

    init {
        fetchEventos()
    }

    private fun fetchEventos() {
        viewModelScope.launch {
            eventoRepository.getAllEventoStream()
                .catch { exception ->
                    _uiState.value = EventoUiState.Error("Error al cargar los eventos: ${exception.message}")
                }
                .collect { eventos ->
                    _uiState.value = EventoUiState.Success(eventos)
                }
        }
    }

    fun insertEvento(evento: Evento) {
        viewModelScope.launch {
            try {
                eventoRepository.insertEvento(evento)
                fetchEventos() // Refresca la lista después de insertar un evento.
            } catch (e: Exception) {
                _uiState.value = EventoUiState.Error("Error al insertar evento: ${e.message}")
            }
        }
    }
}
