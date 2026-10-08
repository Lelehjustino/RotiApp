
package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.myapplication.ui.theme.Meta

@Composable
fun TelaMetas(
    navController: NavHostController,
    viewModel: MetasViewModel = viewModel()
) {

    var mostrarDialogo by remember {
        mutableStateOf(false)
    }

    val total = viewModel.metas.size

    val concluidas = viewModel.metas.count {
        it.concluida
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3EFE0))
            .padding(horizontal = 20.dp)
    ) {

        // ==========================================
        // TOPO
        // ==========================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar"
                )
            }

            Text(
                text = "Minhas metas",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }


        Spacer(
            modifier = Modifier.height(16.dp)
        )

        var metaSelecionada by remember { mutableStateOf<Meta?>(null) }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // ==========================================
            // RESUMO
            // ==========================================

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "$concluidas de $total metas concluídas",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = if (total == 0) {
                                "Crie sua primeira meta!"
                            } else {
                                "Continue avançando!"
                            },
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }
                }
            }


            // ==========================================
            // NOVA META
            // ==========================================

            item {

                Button(
                    onClick = {
                        mostrarDialogo = true
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp),

                    shape = RoundedCornerShape(16.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF388E3C)
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null
                    )

                    Spacer(
                        modifier = Modifier.size(8.dp)
                    )

                    Text(
                        text = "Nova meta",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }


            // ==========================================
            // METAS
            // ==========================================

            items(
                items = viewModel.metas,
                key = { it.idMeta }
            ) { meta ->


                CardMeta(
                    meta = meta,

                    onClick = {
                        metaSelecionada = meta
                    },

                    onCheck = {
                        viewModel.alternarMeta(meta)
                    },

                    onDelete = {
                        viewModel.removerMeta(meta)
                    }
                )


            }
        }

        metaSelecionada?.let { meta ->

            AlertDialog(
                onDismissRequest = {
                    metaSelecionada = null
                },

                title = {
                    Text(
                        text = meta.nomeMeta,
                        fontWeight = FontWeight.Bold
                    )
                },

                text = {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        Text(
                            text = if (meta.descricaoMeta.isBlank()) {
                                "Sem descrição."
                            } else {
                                meta.descricaoMeta
                            }
                        )

                        HorizontalDivider()

                        Text(
                            text = "Status: ${
                                if (meta.concluida) {
                                    "Concluída"
                                } else {
                                    "Em andamento"
                                }
                            }",
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "ID da meta: ${meta.idMeta}"
                        )
                    }
                },

                confirmButton = {
                    TextButton(
                        onClick = {
                            metaSelecionada = null
                        }
                    ) {
                        Text("Fechar")
                    }
                }
            )
        }

    }


    // ==========================================
    // DIALOGO
    // ==========================================

    if (mostrarDialogo) {

        DialogoNovaMeta(

            onDismiss = {
                mostrarDialogo = false
            },

            onConfirm = { nome, descricao ->

                viewModel.adicionarMeta(
                    nome = nome,
                    descricao = descricao
                )

                mostrarDialogo = false
            }
        )
    }
}


// ============================================================
// CARD DA META
// ============================================================

@Composable
fun CardMeta(
    meta: Meta,
    onClick: () -> Unit,
    onCheck: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(18.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Checkbox(
                checked = meta.concluida,

                onCheckedChange = {
                    onCheck()
                }
            )


            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = meta.nomeMeta,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                if (meta.descricaoMeta.isNotEmpty()) {

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = meta.descricaoMeta,
                        fontSize = 13.sp,
                        color = Color.Gray
                    )
                }
            }


            IconButton(
                onClick = onDelete
            ) {

                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Excluir",
                    tint = Color(0xFFE53935)
                )
            }
        }
    }
}


// ============================================================
// DIALOGO NOVA META
// ============================================================

@Composable
fun DialogoNovaMeta(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {

    var nome by remember {
        mutableStateOf("")
    }

    var descricao by remember {
        mutableStateOf("")
    }


    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text(
                text = "Nova meta",
                fontWeight = FontWeight.Bold
            )
        },

        text = {

            Column {

                OutlinedTextField(
                    value = nome,

                    onValueChange = {
                        nome = it
                    },

                    label = {
                        Text("Nome da meta")
                    },

                    singleLine = true,

                    modifier = Modifier.fillMaxWidth()
                )


                Spacer(
                    modifier = Modifier.height(12.dp)
                )


                OutlinedTextField(
                    value = descricao,

                    onValueChange = {
                        descricao = it
                    },

                    label = {
                        Text("Descrição")
                    },

                    modifier = Modifier.fillMaxWidth()
                )
            }
        },

        confirmButton = {

            TextButton(
                onClick = {

                    if (nome.isNotBlank()) {

                        onConfirm(
                            nome,
                            descricao
                        )
                    }
                }
            ) {

                Text("Adicionar")
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {

                Text("Cancelar")
            }
        }
    )
}

