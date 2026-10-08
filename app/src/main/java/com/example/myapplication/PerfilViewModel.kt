
package com.example.myapplication

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.theme.Perfil

class PerfilViewModel : ViewModel() {

    // Dados do perfil
    var perfil = mutableStateOf(Perfil())
        private set

    // Verifica se o usuário já possui cadastro
    fun possuiCadastro(): Boolean {
        return perfil.value.nome.isNotBlank() &&
                perfil.value.email.isNotBlank()
    }

    // Salvar ou atualizar perfil
    fun salvarPerfil(
        nome: String,
        email: String
    ) {
        perfil.value = Perfil(
            nome = nome.trim(),
            email = email.trim()
        )
    }

    // Alterar somente o nome
    fun alterarNome(nome: String) {
        perfil.value = perfil.value.copy(
            nome = nome
        )
    }

    // Alterar somente o e-mail
    fun alterarEmail(email: String) {
        perfil.value = perfil.value.copy(
            email = email
        )
    }
}

