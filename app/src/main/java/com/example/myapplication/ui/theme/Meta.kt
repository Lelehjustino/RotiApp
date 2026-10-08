
package com.example.myapplication.ui.theme

import androidx.compose.runtime.mutableStateListOf

data class Meta(
    val idMeta: Int,
    var nomeMeta: String,
    var descricaoMeta: String,
    var concluida: Boolean = false
)

// Lista global e REATIVA de metas
val listaMetas = mutableStateListOf<Meta>()



