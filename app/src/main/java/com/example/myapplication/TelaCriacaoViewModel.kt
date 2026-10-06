package com.example.myapplication

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.theme.Atividade
import com.example.myapplication.ui.theme.Rotina
import com.example.myapplication.ui.theme.listaAtividades as listaAtividadesGlobal
import com.example.myapplication.ui.theme.listaRotinas as listaRotinasGlobal

class TelaCriacaoViewModel : ViewModel() {

    // Estados do formulário
    var nomeRotina = androidx.compose.runtime.mutableStateOf("")
        private set

    var inicio = androidx.compose.runtime.mutableStateOf("08:00")
        private set

    var fim = androidx.compose.runtime.mutableStateOf("09:00")
        private set

    var nomeAtividade = androidx.compose.runtime.mutableStateOf("")
        private set

    var duracaoAtividade = androidx.compose.runtime.mutableStateOf("")
        private set


    // Lista reativa de atividades da rotina que está sendo criada
    val atividades = mutableStateListOf<Atividade>()


    // Próximo ID da rotina
    fun proximoIdRotina(): Int {
        return if (listaRotinasGlobal.isEmpty()) {
            1
        } else {
            listaRotinasGlobal.maxOf { it.idRotina } + 1
        }
    }


    // Alterações dos campos
    fun alterarNomeRotina(valor: String) {
        nomeRotina.value = valor
    }

    fun alterarInicio(valor: String) {
        inicio.value = valor
    }

    fun alterarFim(valor: String) {
        fim.value = valor
    }

    fun alterarNomeAtividade(valor: String) {
        nomeAtividade.value = valor
    }

    fun alterarDuracaoAtividade(valor: String) {
        duracaoAtividade.value = valor.filter { it.isDigit() }
    }


    // Adiciona uma atividade à lista
    fun adicionarAtividade(): Boolean {

        if (
            nomeAtividade.value.isBlank() ||
            duracaoAtividade.value.isBlank()
        ) {
            return false
        }

        val duracao = duracaoAtividade.value.toDoubleOrNull()

        if (duracao == null || duracao <= 0) {
            return false
        }

        val novoId = if (atividades.isEmpty()) {
            1
        } else {
            atividades.maxOf { it.idAtividade } + 1
        }

        val novaAtividade = Atividade(
            idAtividade = novoId,
            nomeAtividade = nomeAtividade.value,
            duracaoMinutos = duracao
        )

        atividades.add(novaAtividade)

        // Limpa os campos
        nomeAtividade.value = ""
        duracaoAtividade.value = ""

        return true
    }


    // Remove uma atividade
    fun removerAtividade(id: Int) {
        atividades.removeAll {
            it.idAtividade == id
        }
    }


    // Salva a rotina inteira
    fun salvarRotina(): Rotina? {

        if (nomeRotina.value.isBlank()) {
            return null
        }

        val idRotina = proximoIdRotina()

        val tempoTotal = atividades.sumOf {
            it.duracaoMinutos
        }

        val novaRotina = Rotina(
            idRotina = idRotina,
            nomeRotina = nomeRotina.value,
            tempoMinutosRotina = tempoTotal
        )

        // Adiciona a rotina
        listaRotinasGlobal.add(novaRotina)

        // Adiciona as atividades vinculadas à rotina
        atividades.forEach { atividade ->

            val atividadeSalva = atividade.copy(
                idRotina = idRotina
            )

            listaAtividadesGlobal.add(atividadeSalva)
        }

        return novaRotina
    }
}