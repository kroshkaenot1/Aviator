package com.smh.aviator.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.smh.aviator.R
import kotlinx.coroutines.delay
import kotlin.random.Random

val SCORE_FONT = FontFamily(Font(R.font.rockwell_extra_bold))

@Composable
fun Game(
    modifier: Modifier = Modifier,
    navController: NavController,
    score: MutableState<Int>
) {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val density = LocalDensity.current.density
    val screenWidthPx = (screenWidthDp * density).value
    val screenHeightPx = (screenHeightDp * density).value.toInt()

    var planeOffsetX by remember { mutableStateOf(screenWidthPx / 2 - 100) }
    val planeOffsetY by remember { mutableStateOf(screenHeightPx - 300) }

    var enemyPlaneOffsetX by remember { mutableStateOf(Random.nextInt(0, screenWidthPx.toInt() - 100)) }
    var enemyPlaneOffsetY by remember { mutableStateOf(200) }

    var bulletOffsetX by remember { mutableStateOf(planeOffsetX.toInt() + 90) }
    var bulletOffsetY by remember { mutableStateOf(planeOffsetY + 100) }

    var enemyBulletOffsetX by remember { mutableStateOf(enemyPlaneOffsetX + 90) }
    var enemyBulletOffsetY by remember { mutableStateOf(enemyPlaneOffsetY + 200) }

    val healthPoints = remember {
        mutableStateOf(12)
    }
    val enemyMoveSpeed = 4
    val moveSpeed = 6
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.gamebg), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
        Column(modifier = modifier.height(80.dp)) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.navigate("startMenu") },
                    modifier = modifier.weight(1f),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.menu),
                        contentDescription = null
                    )
                }
                Column(
                    modifier = modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.text_score01),
                        contentDescription = null,
                        modifier = modifier.padding(top = 10.dp, bottom = 3.dp)
                    )
                    Box(modifier = modifier.padding(bottom = 6.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.score),
                            contentDescription = null
                        )
                        Text(
                            text = score.value.toString(),
                            modifier = modifier.align(Alignment.Center),
                            fontFamily = SCORE_FONT,
                            color = Color.White,
                            style = TextStyle(fontSize = 20.sp)
                        )
                    }
                }
                Box(modifier = modifier.weight(1f)) {
                    Image(
                        painter = painterResource(id = R.drawable.health),
                        contentDescription = null,
                        modifier = modifier
                    )
                    Row(modifier = modifier.padding(start = 28.dp)) {
                        for (i in 1..healthPoints.value) {
                            if (i < 5) {
                                Image(
                                    painter = painterResource(id = R.drawable.health_point01),
                                    contentDescription = null,
                                    modifier = modifier.padding(start = 2.dp, top = 7.dp)
                                )
                            } else if (i in 5..8) {
                                Image(
                                    painter = painterResource(id = R.drawable.health_point02),
                                    contentDescription = null,
                                    modifier = modifier.padding(start = 2.dp, top = 7.dp)
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = R.drawable.health_point03),
                                    contentDescription = null,
                                    modifier = modifier.padding(start = 2.dp, top = 7.dp)
                                )
                            }
                        }
                    }
                }
            }
            Image(
                painter = painterResource(id = R.drawable.barline), contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxWidth()
            )
        }
        Image(painter = painterResource(id = R.drawable.plane_enemy), contentDescription = null,
            modifier = modifier.offset {
                IntOffset(
                    enemyPlaneOffsetX,
                    enemyPlaneOffsetY
                )
            }
                .clickable { healthPoints.value = 0 })
        Image(painter = painterResource(id = R.drawable.bullet), contentDescription = null,
            modifier = modifier.offset { IntOffset(bulletOffsetX, bulletOffsetY) })
        Image(painter = painterResource(id = R.drawable.bullet_enemy), contentDescription = null,
            modifier = modifier.offset { IntOffset(enemyBulletOffsetX, enemyBulletOffsetY) })
        Image(
            painter = painterResource(id = R.drawable.plane), contentDescription = null,
            modifier = modifier
                .offset { IntOffset(planeOffsetX.toInt(), planeOffsetY) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta -> planeOffsetX += delta }
                ),
        )
    }
    LaunchedEffect(enemyPlaneOffsetY){
        while (enemyBulletOffsetY < screenHeightPx){
            enemyBulletOffsetY += enemyMoveSpeed
            if (enemyBulletOffsetX >= planeOffsetX && enemyBulletOffsetX <= planeOffsetX + 200
                && enemyPlaneOffsetY <= planeOffsetY && enemyBulletOffsetY >= planeOffsetY + 200){
                enemyBulletOffsetY = enemyPlaneOffsetY + 200
                enemyBulletOffsetX = enemyPlaneOffsetX + 90

                healthPoints.value -= 1
            }
            delay(1)
        }
        enemyBulletOffsetY = enemyPlaneOffsetY + 200
        enemyBulletOffsetX = enemyPlaneOffsetX + 90
    }
    LaunchedEffect(enemyPlaneOffsetY) {
        while (enemyPlaneOffsetY < screenHeightPx - 200) {
            enemyPlaneOffsetY += enemyMoveSpeed
            delay(3)
        }
        enemyPlaneOffsetY = 200
        enemyPlaneOffsetX = Random.nextInt(0, screenWidthPx.toInt() - 200)
    }
    LaunchedEffect(bulletOffsetY) {
        while (bulletOffsetY > 165) {
            bulletOffsetY -= moveSpeed
            if (bulletOffsetX >= enemyPlaneOffsetX && bulletOffsetX <= enemyPlaneOffsetX + 200
                && bulletOffsetY >= enemyPlaneOffsetY  && bulletOffsetY <= enemyPlaneOffsetY + 200
            ) {
                enemyPlaneOffsetY = 200
                enemyPlaneOffsetX = Random.nextInt(0, screenWidthPx.toInt() - 200)

                bulletOffsetY = planeOffsetY - 100
                bulletOffsetX = planeOffsetX.toInt() + 90
                score.value += 1
            }
            delay(1)
        }
        bulletOffsetY = planeOffsetY - 100
        bulletOffsetX = planeOffsetX.toInt() + 90
    }
    if (healthPoints.value <= 0) {
        navController.navigate("endgame")
        healthPoints.value = 12
    }
}