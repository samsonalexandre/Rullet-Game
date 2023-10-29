package com.example.rulletgame.rule_screen

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rulletgame.R
import com.example.rulletgame.utils.NumberUtil
import kotlin.math.roundToInt


@Composable
fun RuleScreen() {

    var rotationValue by remember {
        mutableStateOf(0f)
    }

    var number by remember {
        mutableStateOf(0)
    }

    val angle: Float by animateFloatAsState(
        targetValue = rotationValue,
        animationSpec = tween(
            durationMillis = 2000,
            easing = LinearOutSlowInEasing
        ),
        finishedListener = {
            val index = (360 - (it % 360)) / (360f / NumberUtil.list.size)
            number = NumberUtil.list[index.roundToInt()]
        },
        label = ""
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .wrapContentHeight()
                .wrapContentWidth(),
            text = number.toString(),
            fontWeight = FontWeight.Bold,
            fontSize = 35.sp,
            color = Color.White
        )
        Box(modifier = Modifier
            .weight(1f)
            .fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.roulette),
                contentDescription = "Roulette",
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(angle)
            )
            Image(painter = painterResource(id = R.drawable.pfeil),
                contentDescription = "Pfeil",
                modifier = Modifier
                    .fillMaxSize()

            )
        }
        Button(onClick = {
                         rotationValue = (720..1080).random().toFloat() + angle
        },
            colors = ButtonDefaults.buttonColors(),  //(backgroundColor = Red),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "Start",
                color = Color.White)

        }
    }
}