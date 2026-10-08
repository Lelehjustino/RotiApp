
package com.example.myapplication.ui.theme

import androidx.compose.runtime.mutableStateListOf

data class Rotina(
    val idRotina: Int,
    var nomeRotina: String,
    var tempoMinutosRotina: Double
)

// Lista global e REATIVA de rotinas
val listaRotinas = mutableStateListOf<Rotina>()

