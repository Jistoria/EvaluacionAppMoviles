package com.example.evaluacionprime.ui.evento

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.evaluacionprime.data.entity.Evento
import com.example.evaluacionprime.provider.AppViewModelProvider

@Composable
fun EventoScreenFetch(
    navController : NavHostController,
    viewModel: EventoViewModel = viewModel(factory = AppViewModelProvider.Factory)  ) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is EventoUiState.Loading -> {
            CircularProgressIndicator()
        }
        is EventoUiState.Success -> {
            val eventos = (uiState as EventoUiState.Success).eventos
            EventoList(eventos, navController)
        }
        is EventoUiState.Error -> {
            val message = (uiState as EventoUiState.Error).message
            Text(text = "Error: $message")
        }
    }
}

@Composable
fun EventoList(eventos: List<Evento>, navController: NavHostController) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        items(eventos) { evento ->
            EventoItem(evento) { evento ->
                navController.navigate("asistentes/${evento.id}")
            }
        }
    }
}

@Composable
fun EventoItem(evento: Evento, onEventoClick: (Evento) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onEventoClick(evento) }, // Maneja el clic
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = evento.nombre, style = MaterialTheme.typography.bodySmall)
            Text(text = "Horas: ${evento.horas}")
        }
    }
}


