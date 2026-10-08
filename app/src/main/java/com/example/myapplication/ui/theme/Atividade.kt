
package com.example.myapplication.ui.theme

import androidx.compose.runtime.mutableStateListOf

data class Atividade(
    var idAtividade: Int = 0,
    var idRotina: Int = 0,
    var nomeAtividade: String = "",
    var duracaoMinutos: Double = 0.0
)

// Lista global e REATIVA de atividades
val listaAtividades = mutableStateListOf<Atividade>()

fun calcularXp(): Int {
    return listaAtividades.sumOf {
        it.duracaoMinutos.toInt()
    }
}

fun calcularNivel(): Int {
    val xp = calcularXp()
    return (xp / 100) + 1
}

fun calcularXpNoNivel(): Int {
    val xp = calcularXp()
    return xp % 100
}
