package com.drg.rustandrevolt.ui.screens.combat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.drg.rustandrevolt.R
import com.drg.rustandrevolt.ui.screens.home.TYPEFACE
import com.drg.rustandrevolt.viewmodels.CombatViewModel

@Composable
fun GameScreen(viewModel : CombatViewModel = hiltViewModel()) {
    val context = LocalContext.current
    viewModel.context = context

    var enemyAIName= viewModel.mutableEnemyAIName
    var enemyAILife = viewModel.mutableEnemyAILife
    var enemyAIDamage = viewModel.mutableEnemyAIDamage
    var playerDamage = viewModel.mutablePlayerDamage
    var playerDamageRedColor = viewModel.mutablePlayerDamageRedColor
    var enemyAIDamageRedColor = viewModel.mutableEnemyAIDamageRedColor
    var playerImage : Int = viewModel.imagePlayerCombat
    var enemyImage : Int = viewModel.imageEnemyCombat

    //Pantalla de juego


    //Columna -> Nombre Enemigo + Vida Enemigo + box imagenes personajes
    Column(modifier = Modifier
        .fillMaxWidth()
        .border(1.dp, Color.Red)
        .fillMaxHeight(0.7f)
        .background(Color.White)
    ) {
        //Text(text = "Pantalla juego", fontSize = 15.sp)

        //Texto Nombre Enemigo
        Text(
            text = enemyAIName,
            color = Color.White,
            style = TextStyle(
                fontFamily = FontFamily(TYPEFACE),
                fontSize = 20.sp,
                letterSpacing = 2.sp,
                shadow = Shadow(
                    color = Color.Black, // Color del borde
                    blurRadius = 10f, // Radio del desenfoque
                    offset = Offset(1f, 1f) // Desplazamiento del borde
                )
            )
        )

        //Vida Enemigo
        LinearProgressIndicator(
            progress = enemyAILife,
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp),
            color = if (enemyAILife < 0.25f) Color.Red else if (enemyAILife < 0.5f && enemyAILife > 0.3f) Color.Yellow else Color.Green
        )

        //Contenedor de Imagenes de Personajes en la batalla
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            //Fila Imagen enemigo + daño
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(1.dp, Color.Black),
                horizontalArrangement = Arrangement.End

            ){
                Text(
                    text = enemyAIDamage,
                    fontSize = 50.sp,
                    modifier = Modifier
                        .border(1.dp, Color.Black)
                        .align(Alignment.CenterVertically),
                    color = if (enemyAIDamageRedColor) Color.Red else Color.Green
                )


                Image(
                    painter = painterResource(enemyImage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 150.dp, height = 300.dp)
                        .border(1.dp, Color.Black)
                )
            }

            //Fila Imagen jugador + daño
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .border(1.dp, Color.Black).align(Alignment.BottomStart)
            ) {
                Image(
                    painter = painterResource(playerImage),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 230.dp, height = 175.dp)
                        .border(1.dp, Color.Black)
                        .align(Alignment.Bottom)
                )
                Text(
                    text = playerDamage,
                    fontSize = 50.sp,
                    modifier = Modifier
                        .border(1.dp, Color.Black)
                        .align(Alignment.CenterVertically),
                    color = if (playerDamageRedColor) Color.Red else Color.Green
                )
            }
        }
    }
}