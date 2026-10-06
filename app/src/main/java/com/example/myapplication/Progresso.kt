package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.ui.theme.calcularNivel
import com.example.myapplication.ui.theme.calcularXp
import com.example.myapplication.ui.theme.calcularXpNoNivel


// ============================================================
// NAVEGAÇÃO PRINCIPAL
// ============================================================

@Composable
fun AppNavegacao() {

    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFF3EFE0),

        bottomBar = {
            BarraNavegacao(navController)
        }

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = RotaAbas.TelaListaRotina,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            // Tela inicial
            composable(RotaAbas.TelaListaRotina) {
                TelaListaRotinas(navController)
            }

            // Progresso
            composable(RotaAbas.Progresso) {
                Progresso(navController)
            }

            // Perfil
            composable(RotaAbas.TelaPerfil) {
                TelaPerfil(navController)
            }

            // Criar nova rotina
            composable(RotaAbas.TelaCriacao) {
                TelaCriacao(navController)
            }

            // Detalhes da rotina
            composable(RotaAbas.TelaRotina) {
                TelaRotina(navController)
            }

            // Histórico
            composable(RotaAbas.TelaHistorico) {
                TelaHistorico(navController)
            }

            // Metas
            composable(RotaAbas.TelaMetas) {
                TelaMetas(navController)
            }

            // Perfil cont
            composable(RotaAbas.TelaPerfilCont) {
                TelaPerfilCont(navController)
            }
        }
    }
}


// TELA DE PROGRESSO

@Composable
fun Progresso(navController: NavHostController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF3EFE0))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "Seu progresso",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E1E1E)
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        CardNivel()

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        CardMetricas()

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        // CardAtividadeGrid()

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        CardMetasHistorico(navController)
    }
}


// ============================================================
// CARD NÍVEL
// ============================================================

@Composable
fun CardNivel() {

    val xp = calcularXp()
    val nivel = calcularNivel()
    val xpNoNivel = calcularXpNoNivel()

    val progresso = xpNoNivel / 100f

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = Color(0xFFFFF3E0),
                    modifier = Modifier.size(44.dp)
                ) {

                    Box(
                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "🔥",
                            fontSize = 20.sp
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Column {

                    Text(
                        text = "Nível $nivel",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "$xp XP acumulado",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            LinearProgressIndicator(
                progress = { progresso },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp)
                    .clip(CircleShape),

                color = Color(0xFF4CAF50),
                trackColor = Color(0xFFE0E0E0)
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "$xpNoNivel / 100 XP",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {

                Button(
                    onClick = { },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEFEBE0)
                    ),

                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.height(36.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Salvar",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(4.dp)
                    )

                    Text(
                        text = "Salvar",
                        color = Color.Black,
                        fontSize = 13.sp
                    )
                }

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Button(
                    onClick = { },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFEFEBE0)
                    ),

                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.height(36.dp)
                ) {

                    Icon(
                        imageVector = Icons.Default.Share,
                        contentDescription = "Compartilhar",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )

                    Spacer(
                        modifier = Modifier.width(4.dp)
                    )

                    Text(
                        text = "Compartilhar",
                        color = Color.Black,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}


// ============================================================
// CARD MÉTRICAS
// ============================================================

@Composable
fun CardMetricas() {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White
    ) {

        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color(0xFFE3F2FD),
                modifier = Modifier.size(44.dp)
            ) {

                Box(
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = null,
                        tint = Color(0xFF2196F3)
                    )
                }
            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Text(
                text = "Acompanhe suas métricas",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


// ============================================================
// CARD ATIVIDADE
// ============================================================

@Composable
fun CardAtividadeGrid() {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "ATIVIDADE — ÚLTIMAS 10 SEMANAS",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            val tonsVerde = listOf(
                Color(0xFFF0F0F0),
                Color(0xFFC8E6C9),
                Color(0xFF81C784),
                Color(0xFF4CAF50),
                Color(0xFF2E7D32)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                repeat(7) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        repeat(10) {

                            val nivelColor = (0..4).random()

                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .clip(
                                        RoundedCornerShape(4.dp)
                                    )
                                    .background(
                                        tonsVerde[nivelColor]
                                    )
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Menos",
                    fontSize = 11.sp,
                    color = Color.Gray
                )

                Spacer(
                    modifier = Modifier.width(4.dp)
                )

                tonsVerde.forEach { color ->

                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(
                                RoundedCornerShape(2.dp)
                            )
                            .background(color)
                    )

                    Spacer(
                        modifier = Modifier.width(3.dp)
                    )
                }

                Text(
                    text = "Mais",
                    fontSize = 11.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


// ============================================================
// BARRA DE NAVEGAÇÃO
// ============================================================

@Composable
fun BarraNavegacao(
    navInterno: NavHostController
) {

    val rotaAtual =
        navInterno.currentBackStackEntryAsState()
            .value
            ?.destination
            ?.route

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = Color(0xFFF3EFE0)
    ) {

        NavigationBarItem(
            selected = rotaAtual == RotaAbas.TelaListaRotina,

            onClick = {
                navInterno.navigate(RotaAbas.TelaListaRotina) {
                    popUpTo(RotaAbas.TelaListaRotina) {
                        inclusive = false
                    }
                    launchSingleTop = true
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Rotinas"
                )
            },

            label = {
                Text("Rotinas")
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF4CAF50),
                selectedTextColor = Color(0xFF4CAF50),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = rotaAtual == RotaAbas.Progresso,

            onClick = {
                navInterno.navigate(RotaAbas.Progresso) {
                    popUpTo(RotaAbas.TelaListaRotina) {
                        inclusive = false
                    }
                    launchSingleTop = true
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription = "Progresso"
                )
            },

            label = {
                Text("Progresso")
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF4CAF50),
                selectedTextColor = Color(0xFF4CAF50),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )

        NavigationBarItem(
            selected = rotaAtual == RotaAbas.TelaPerfil,

            onClick = {
                navInterno.navigate(RotaAbas.TelaPerfil) {
                    popUpTo(RotaAbas.TelaListaRotina) {
                        inclusive = false
                    }
                    launchSingleTop = true
                }
            },

            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Perfil"
                )
            },

            label = {
                Text("Perfil")
            },

            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF4CAF50),
                selectedTextColor = Color(0xFF4CAF50),
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}

// ============================================================
// CARD METAS E HISTÓRICO
// ============================================================

@Composable
fun CardMetasHistorico(
    navController: NavHostController
) {

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = "Acompanhe sua jornada",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E1E)
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Consulte suas metas e veja seu histórico.",
                fontSize = 13.sp,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Button(
                    onClick = {
                        navController.navigate(RotaAbas.TelaMetas)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE8F5E9)
                    )
                ) {

                    Text(
                        text = "Metas",
                        color = Color(0xFF388E3C),
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        navController.navigate(RotaAbas.TelaHistorico)
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE3F2FD)
                    )
                ) {

                    Text(
                        text = "Histórico",
                        color = Color(0xFF1976D2),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
