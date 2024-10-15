package com.example.evaluacionprime.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.evaluacionprime.ui.asistente.AsistenteScreenFetch
import com.example.evaluacionprime.ui.evento.EventoScreenFetch

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "eventos") {
        composable("eventos") {
            EventoScreenFetch(navController = navController)
        }
        composable("asistentes/{eventoId}") { backStackEntry ->
            val eventoId = backStackEntry.arguments?.getString("eventoId")?.toInt() ?: 0
            AsistenteScreenFetch(eventoId = eventoId)
        }
    }
}