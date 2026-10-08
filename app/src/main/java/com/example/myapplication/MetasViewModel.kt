
package com.example.myapplication

import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.theme.Meta
import com.example.myapplication.ui.theme.listaMetas
import com.example.myapplication.ui.theme.registrarMetaNoHistorico

class MetasViewModel : ViewModel() {

    // Lista compartilhada pelo aplicativo
    val metas = listaMetas


    // ==========================================
    // PRÓXIMO ID
    // ==========================================

    private fun proximoId(): Int {

        if (metas.isEmpty()) {
            return 1
        }

        return metas.maxOf {
            it.idMeta
        } + 1
    }


    // ==========================================
    // ADICIONAR META
    // ==========================================

    fun adicionarMeta(
        nome: String,
        descricao: String
    ) {

        if (nome.isBlank()) {
            return
        }

        val novaMeta = Meta(
            idMeta = proximoId(),
            nomeMeta = nome.trim(),
            descricaoMeta = descricao.trim(),
            concluida = false
        )

        metas.add(novaMeta)
    }


    // ==========================================
    // CONCLUIR / DESMARCAR META
    // ==========================================

    fun alternarMeta(meta: Meta) {

        val indice = metas.indexOfFirst {
            it.idMeta == meta.idMeta
        }

        if (indice != -1) {

            val metaAtual = metas[indice]

            val novaConclusao = !metaAtual.concluida

            metas[indice] = metaAtual.copy(
                concluida = novaConclusao
            )

            // Só registra no histórico quando CONCLUI
            if (novaConclusao) {
                registrarMetaNoHistorico(
                    metaAtual.nomeMeta
                )
            }
        }
    }



    // ==========================================
    // EXCLUIR META
    // ==========================================

    fun removerMeta(meta: Meta) {

        metas.removeAll {
            it.idMeta == meta.idMeta
        }
    }


    // ==========================================
    // BUSCAR UMA META
    // ==========================================

    fun buscarMeta(idMeta: Int): Meta? {

        return metas.find {
            it.idMeta == idMeta
        }
    }


    // ==========================================
    // ATUALIZAR META
    // ==========================================

    fun atualizarMeta(
        idMeta: Int,
        nome: String,
        descricao: String
    ) {

        val indice = metas.indexOfFirst {
            it.idMeta == idMeta
        }

        if (indice != -1) {

            metas[indice] = metas[indice].copy(
                nomeMeta = nome.trim(),
                descricaoMeta = descricao.trim()
            )
        }
    }
}
