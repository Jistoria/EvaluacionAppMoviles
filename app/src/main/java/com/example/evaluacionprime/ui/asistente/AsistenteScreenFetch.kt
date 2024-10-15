package com.example.evaluacionprime.ui.asistente

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.evaluacionprime.data.entity.Asistente
import com.example.evaluacionprime.provider.AppViewModelProvider

@Composable
fun AsistenteScreenFetch(
    eventoId: Int,
    viewModel: AsistenteViewModel = viewModel(factory = AppViewModelProvider.AsistenteFactory) ) {
    LaunchedEffect(eventoId) {
        viewModel.fetchAsistentesByEventoId(eventoId)
    }
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is AsistenteUiState.Loading -> {
            CircularProgressIndicator()
        }
        is AsistenteUiState.Success -> {
            val asistentes = (uiState as AsistenteUiState.Success).asistentes
            AsistenteList(asistentes)
        }
        is AsistenteUiState.Error -> {
            val message = (uiState as AsistenteUiState.Error).message
            Text(text = "Error: $message")
        }
    }
}

@Composable
fun AsistenteList(asistentes: List<Asistente>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(asistentes) { asistente ->
            AsistenteItem(asistente)
        }
    }
}

@Composable
fun AsistenteItem(asistente: Asistente) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${asistente.nombre} ${asistente.apellido}", style = MaterialTheme.typography.displaySmall)
            Text(text = "Edad: ${asistente.edad}, Género: ${asistente.genero?.name ?: "No especificado"}")
        }
    }
}