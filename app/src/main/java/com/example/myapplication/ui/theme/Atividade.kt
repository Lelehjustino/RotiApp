package com.example.myapplication.ui.theme

data class Atividade(
    var idAtividade: Int = 0,
    var idRotina: Int = 0,
    var nomeAtividade: String = "",
    var duracaoMinutos: Double = 0.0
)

// Lista para armazenar as atividades
val listaAtividades = mutableListOf<Atividade>()

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