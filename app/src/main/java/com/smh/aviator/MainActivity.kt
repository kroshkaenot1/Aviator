package com.smh.aviator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smh.aviator.presentation.EndGame
import com.smh.aviator.presentation.Game
import com.smh.aviator.presentation.StartMenu

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val score = remember {
                mutableStateOf(0)
            }
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "startMenu"){
                composable("startMenu"){
                    StartMenu(navController = navController)
                }
                composable("game"){
                    Game(navController = navController, score = score)
                }
                composable("endgame"){
                    EndGame(navController = navController, score = score)
                }
            }
        }
    }
}
