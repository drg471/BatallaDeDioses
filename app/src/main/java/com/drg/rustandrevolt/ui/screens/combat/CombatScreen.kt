package com.drg.rustandrevolt.ui.screens.combat

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.drg.rustandrevolt.ui.navigation.AppScreens

@Composable
fun CombatScreen(
    viewModel : CombatViewModel = hiltViewModel(),
    navigateToHomeScreen : () -> Unit) {
    val state = viewModel.state.collectAsState()
    CombatContent(
        state,
        navigateToHomeScreen
    )
}

@Composable
fun CombatContent(
    state: CombatState,
    navigateToHomeScreen: () -> Unit
){
    when(state){
        CombatState.Loading -> TODO()
        is CombatState.Playing -> TODO()
        is CombatState.StopPlaying -> TODO()
    }
    //Columna Principal
    Column (modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    ){

        //Contenedor pantalla de juego
        GameScreen()

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        //Contenedor controles del jugador
        PlayerControls(navigateToHomeScreen)
    }
}

@Preview
@Composable
fun CombatScreenPreview() {
    val navController = rememberNavController()

    fun navigateToHomeScreen() {
        navController.navigate(AppScreens.HomeScreen.route)
    }
    fun navigateToSelectCharacterScreen() {
        navController.navigate(AppScreens.HomeScreen.route)
    }

    CombatScreen(
        navigateToHomeScreen = { navigateToHomeScreen() }
    )
}