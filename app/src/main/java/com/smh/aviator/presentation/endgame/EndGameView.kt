package com.smh.aviator.presentation.endgame

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.smh.aviator.R
import com.smh.aviator.presentation.play.SCORE_FONT

@Composable
fun EndGame(
    modifier: Modifier = Modifier,
    navController: NavController,
    score: MutableState<Int>
) {
    val endGameViewModel = hiltViewModel<EndGameViewModel>()

    val bestScoreBeated = endGameViewModel.saveScoreToSharedPreferences(score = score.value)
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background05),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
        Image(
            modifier = modifier
                .padding(14.dp)
                .clickable { navController.navigate("startMenu") },
            painter = painterResource(id = R.drawable.menu),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Box(
            modifier = modifier.align(Alignment.Center),
            contentAlignment = Alignment.Center
        ) {
            ScoreBoard(
                modifier = modifier,
                bestScoreBeated = bestScoreBeated,
                score = score,
                navController = navController
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.align(Alignment.BottomCenter)
            ) {
                Image(
                    modifier = modifier.padding(bottom = 10.dp),
                    painter = painterResource(id = R.drawable.text_best),
                    contentDescription = null
                )
                Box(modifier = modifier.padding(bottom = 15.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.score),
                        contentDescription = null
                    )
                    Text(
                        modifier = modifier.align(Alignment.Center),
                        text = endGameViewModel.getScoreFromSharedPreferences().toString(),
                        fontFamily = SCORE_FONT,
                        color = Color.White,
                        style = TextStyle(fontSize = 20.sp)
                    )
                }
            }
        }
    }
}

@Composable
fun ScoreBoard(
    modifier: Modifier = Modifier,
    bestScoreBeated: Boolean,
    score: MutableState<Int>,
    navController: NavController,
) {
    if (bestScoreBeated) {
        Image(
            painter = painterResource(id = R.drawable.text_box02),
            contentDescription = null
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.text_box01),
            contentDescription = null
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!bestScoreBeated) {
            Image(
                modifier = modifier.padding(bottom = 30.dp),
                painter = painterResource(id = R.drawable.text_plane_crashed),
                contentDescription = null
            )
        } else {
            Image(
                modifier = modifier.padding(6.dp),
                painter = painterResource(id = R.drawable.text_new_best_score),
                contentDescription = null
            )
        }
        Image(
            modifier = modifier.padding(6.dp),
            painter = painterResource(id = R.drawable.text_score02),
            contentDescription = null
        )
        Box(modifier = modifier.padding(6.dp)) {
            Image(
                painter = painterResource(id = R.drawable.score),
                contentDescription = null
            )
            Text(
                modifier = modifier.align(Alignment.Center),
                text = score.value.toString(),
                fontFamily = SCORE_FONT,
                color = Color.White,
                style = TextStyle(fontSize = 20.sp)
            )
        }
        Image(
            modifier = modifier.padding(6.dp),
            painter = painterResource(id = R.drawable.text_play_again),
            contentDescription = null
        )
        Row(
            modifier = modifier
                .padding(top = 70.dp)
        ) {
            Image(
                modifier = modifier
                    .weight(1f)
                    .padding(start = 90.dp)
                    .clickable {
                        navController.navigate("game")
                        score.value = 0
                    },
                painter = painterResource(id = R.drawable.ok),
                contentDescription = null
            )
            Image(
                modifier = modifier
                    .weight(1f)
                    .padding(end = 90.dp)
                    .clickable {
                        navController.navigate("startMenu")
                        score.value = 0
                    },
                painter = painterResource(id = R.drawable.close),
                contentDescription = null
            )
        }
    }
}