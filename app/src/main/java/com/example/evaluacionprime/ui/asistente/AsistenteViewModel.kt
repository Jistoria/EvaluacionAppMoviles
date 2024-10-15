package com.example.evaluacionprime.ui.asistente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.evaluacionprime.data.entity.Asistente
import com.example.evaluacionprime.data.repository.AsistenteRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AsistenteViewModel(private val asistenteRepository: AsistenteRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<AsistenteUiState>(AsistenteUiState.Loading)
    val uiState: StateFlow<AsistenteUiState> = _uiState.asStateFlow()



    fun fetchAsistentesByEventoId(eventoId: Int) {
        viewModelScope.launch {
            asistenteRepository.getAsistentesByEventoId(eventoId)
                .catch { exception ->
                    _uiState.value = AsistenteUiState.Error("Error al cargar los asistentes: ${exception.message}")
                }
                .collect { asistentes ->
                    _uiState.value = AsistenteUiState.Success(asistentes)
                }
        }
    }
}
