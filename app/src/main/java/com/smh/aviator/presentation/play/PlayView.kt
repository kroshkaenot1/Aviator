package com.smh.aviator.presentation.play

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.integerResource
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
    val distanceBetweenPlaneNBottom = integerResource(id = R.integer.distance_between_plane_bottom)
    val enemyPlaneWidth = integerResource(id = R.integer.enemy_plane_width)
    val enemyPlaneHeight = integerResource(id = R.integer.enemy_plane_height)
    val medkitSpawnHeight = integerResource(id = R.integer.medkit_spawn_height)
    val enemyPlaneSpawnHeight = integerResource(id = R.integer.enemy_plane_spawn_height)
    val medkitWidth = integerResource(id = R.integer.medkit_width)
    val medkitHeight = integerResource(id = R.integer.medkit_height)
    val planeWidth = integerResource(id = R.integer.plane_width)
    val planeHeight = integerResource(id = R.integer.plane_height)
    val topBarHeight = integerResource(id = R.integer.topBar_height)
    val healthPointsValue = integerResource(id = R.integer.health_points)
    val pointsForKill = integerResource(id = R.integer.points_for_kill)
    val pointsForSmallMedkit = integerResource(id = R.integer.points_for_small_medkit)
    val chanceForSmallMedkit = integerResource(id = R.integer.chance_for_small_medkit)
    val chanceForBigMedkit = integerResource(id = R.integer.chance_for_big_medkit)
    val enemyDamage = integerResource(id = R.integer.enemy_damage)
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp.dp
    val screenHeightDp = configuration.screenHeightDp.dp

    val density = LocalDensity.current.density
    val screenWidthPx = (screenWidthDp * density).value
    val screenHeightPx = (screenHeightDp * density).value.toInt()

    var planeOffsetX by remember { mutableStateOf(screenWidthPx / 2 - planeWidth) }
    val planeOffsetY by remember { mutableStateOf(screenHeightPx - distanceBetweenPlaneNBottom) }

    var enemyPlaneOffsetX by remember {
        mutableStateOf(
            Random.nextInt(
                0,
                screenWidthPx.toInt() - enemyPlaneWidth
            )
        )
    }
    var enemyPlaneOffsetY by remember { mutableStateOf(-enemyPlaneHeight) }

    var bulletOffsetX by remember { mutableStateOf(planeOffsetX.toInt() + planeWidth/2) }
    var bulletOffsetY by remember { mutableStateOf(planeOffsetY) }

    var enemyBulletOffsetX by remember { mutableStateOf(enemyPlaneOffsetX + enemyPlaneWidth/2) }
    var enemyBulletOffsetY by remember { mutableStateOf(enemyPlaneOffsetY) }

    val smallMedKitOffsetX = remember {
        mutableStateOf(
            Random.nextInt(
                0,
                screenWidthPx.toInt() - medkitWidth
            )
        )
    }
    val smallMedKitOffsetY = remember { mutableStateOf(medkitSpawnHeight) }

    val bigMedKitOffsetX = remember {
        mutableStateOf(
            Random.nextInt(
                0,
                screenWidthPx.toInt() - medkitWidth
            )
        )
    }
    val bigMedKitOffsetY = remember { mutableStateOf(medkitSpawnHeight) }

    val smallMedKitCreated = remember {
        mutableStateOf(false)
    }
    val bigMedKitCreated = remember {
        mutableStateOf(false)
    }
    val healthPoints = remember {
        mutableStateOf(healthPointsValue)
    }

    val objectsMoveSpeed = 4
    val bulletMoveSpeed = 6
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.background05), contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier.fillMaxSize()
        )
        Image(painter = painterResource(id = R.drawable.first_aid_kit_small),
            contentDescription = null,
            modifier = modifier.offset {
                IntOffset(
                    smallMedKitOffsetX.value,
                    smallMedKitOffsetY.value
                )
            })
        Image(painter = painterResource(id = R.drawable.first_aid_kit_big),
            contentDescription = null,
            modifier = modifier.offset {
                IntOffset(
                    bigMedKitOffsetX.value,
                    bigMedKitOffsetY.value
                )
            })
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
            modifier = modifier
                .offset {
                    IntOffset(
                        enemyPlaneOffsetX,
                        enemyPlaneOffsetY
                    )
                })
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
    //medkits movement logic
    MedKitLogic(
        medkitCreated = bigMedKitCreated,
        medkitOffsetX = bigMedKitOffsetX,
        medkitOffsetY = bigMedKitOffsetY,
        screenHeightPx = screenHeightPx,
        screenWidthPx = screenWidthPx,
        enemyMoveSpeed = objectsMoveSpeed
    )
    MedKitLogic(
        medkitCreated = smallMedKitCreated,
        medkitOffsetX = smallMedKitOffsetX,
        medkitOffsetY = smallMedKitOffsetY,
        screenHeightPx = screenHeightPx,
        screenWidthPx = screenWidthPx,
        enemyMoveSpeed = objectsMoveSpeed
    )
    //enemy bullet logic
    LaunchedEffect(enemyPlaneOffsetY) {
        while (enemyBulletOffsetY < screenHeightPx) {
            enemyBulletOffsetY += objectsMoveSpeed
            if (enemyBulletOffsetX >= planeOffsetX && enemyBulletOffsetX <= planeOffsetX + planeWidth
                && enemyBulletOffsetY >= planeOffsetY && enemyBulletOffsetY <= planeOffsetY + planeHeight
            ) {
                enemyBulletOffsetY = enemyPlaneOffsetY + enemyPlaneHeight
                enemyBulletOffsetX = enemyPlaneOffsetX + enemyPlaneWidth/2

                healthPoints.value -= enemyDamage
            }
            delay(1)
        }
        enemyBulletOffsetY = enemyPlaneOffsetY + enemyPlaneHeight
        enemyBulletOffsetX = enemyPlaneOffsetX + enemyPlaneWidth/2
    }
    //enemy plane logic
    LaunchedEffect(enemyPlaneOffsetY) {
        while (enemyPlaneOffsetY < screenHeightPx) {
            enemyPlaneOffsetY += objectsMoveSpeed
            delay(3)
        }
        enemyPlaneOffsetY = -enemyPlaneHeight
        enemyPlaneOffsetX = Random.nextInt(0, screenWidthPx.toInt() - enemyPlaneWidth)
    }
    //bullet logic
    LaunchedEffect(bulletOffsetY) {
        while (bulletOffsetY > topBarHeight) {
            bulletOffsetY -= bulletMoveSpeed
            // enemy hit condition
            if (bulletOffsetX >= enemyPlaneOffsetX && bulletOffsetX <= enemyPlaneOffsetX + enemyPlaneWidth
                && bulletOffsetY >= enemyPlaneOffsetY && bulletOffsetY <= enemyPlaneOffsetY + enemyPlaneHeight
            ) {
                enemyPlaneOffsetY = enemyPlaneSpawnHeight
                enemyPlaneOffsetX = Random.nextInt(0, screenWidthPx.toInt() - enemyPlaneWidth)

                bulletOffsetY = planeOffsetY
                bulletOffsetX = planeOffsetX.toInt() + enemyPlaneWidth/2
                score.value += pointsForKill
                // chance of small medkit
                if (score.value % chanceForSmallMedkit == 0 && score.value != 0) {
                    if (!smallMedKitCreated.value) {
                        smallMedKitCreated.value = true
                    }
                }
                // chance of big medkit
                if (score.value % chanceForBigMedkit == 0 && score.value != 0) {
                    if (!bigMedKitCreated.value) {
                        bigMedKitCreated.value = true
                    }
                }
            }
            // small med kit hit condition
            if (bulletOffsetX >= smallMedKitOffsetX.value && bulletOffsetX <= smallMedKitOffsetX.value + medkitWidth
                && bulletOffsetY >= smallMedKitOffsetY.value && bulletOffsetY <= smallMedKitOffsetY.value + medkitHeight
            ) {
                smallMedKitOffsetY.value = medkitSpawnHeight
                smallMedKitOffsetX.value = Random.nextInt(0, screenWidthPx.toInt() - medkitWidth)

                smallMedKitCreated.value = false

                bulletOffsetY = planeOffsetY
                bulletOffsetX = planeOffsetX.toInt() + planeWidth/2

                healthPoints.value = (healthPoints.value + pointsForSmallMedkit).coerceIn(0, healthPointsValue)

            }
            // big med kit hit condition
            if (bulletOffsetX >= bigMedKitOffsetX.value && bulletOffsetX <= bigMedKitOffsetX.value + medkitWidth
                && bulletOffsetY >= bigMedKitOffsetY.value && bulletOffsetY <= bigMedKitOffsetY.value + medkitHeight
            ) {
                bigMedKitOffsetY.value = medkitSpawnHeight
                bigMedKitOffsetX.value = Random.nextInt(0, screenWidthPx.toInt() - medkitWidth)

                bigMedKitCreated.value = false

                bulletOffsetY = planeOffsetY
                bulletOffsetX = planeOffsetX.toInt() + planeWidth/2
                healthPoints.value = healthPointsValue
            }
            delay(1)
        }
        bulletOffsetY = planeOffsetY
        bulletOffsetX = planeOffsetX.toInt() + planeWidth/2
    }
    if (healthPoints.value <= 0) {
        navController.navigate("endgame")
        healthPoints.value = healthPointsValue
    }
}

@Composable
fun MedKitLogic(
    medkitCreated: MutableState<Boolean>,
    medkitOffsetX: MutableState<Int>,
    medkitOffsetY: MutableState<Int>,
    screenHeightPx: Int,
    screenWidthPx: Float,
    enemyMoveSpeed: Int
) {
    LaunchedEffect(medkitCreated.value) {
        if (medkitCreated.value) {
            while (medkitOffsetY.value < screenHeightPx) {
                medkitOffsetY.value += enemyMoveSpeed
                delay(8)
            }
            medkitCreated.value = false
            medkitOffsetY.value = -100
            medkitOffsetX.value = Random.nextInt(0, screenWidthPx.toInt() - 100)
        }
    }
}
