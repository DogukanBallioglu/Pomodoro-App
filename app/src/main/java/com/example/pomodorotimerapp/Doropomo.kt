package com.example.pomodorotimerapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun MainScreen(
modifier: Modifier = Modifier,
isDark: Boolean,
onThemeChange: (Boolean) -> Unit

) {

    var workMinutes by remember { mutableStateOf(25) }
    var breakMinutes by remember { mutableStateOf(5) }

    var timeLeft by remember { mutableStateOf(workMinutes * 60) }

    var isRunning by remember { mutableStateOf(false) }

    var showSettings by remember { mutableStateOf(false) }
    var isBreak by remember { mutableStateOf(false) }

    val Heavitas = FontFamily(Font(R.font.heavitas))



    LaunchedEffect(isRunning) {
        while (isRunning && timeLeft > 0) {
            delay(1000)
            timeLeft--
        }
    }

    LaunchedEffect(isBreak) {
        timeLeft = if (isBreak) breakMinutes * 60 else workMinutes * 60
    }

    if (showSettings) {
        SettingsScreen(
            isDark = isDark,
            onThemeChange = onThemeChange,
            close = { showSettings = false },
            onSave = { work, breakTime ->
                workMinutes = work
                breakMinutes = breakTime
                timeLeft = if (isBreak) breakMinutes * 60 else workMinutes * 60
            }
        )
        return
    }

    val minutes = timeLeft / 60
    val seconds = timeLeft % 60
    val formattedTime = String.format("%02d:%02d", minutes, seconds)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { isRunning = !isRunning },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.Black)
                .padding(12.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.doropomo_ust_baslik),
                contentDescription = "Üst Başlık",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(175.dp))

        Text(
            text = if (isRunning) "TAP HERE TO PAUSE" else "TAP HERE TO START",
            fontFamily = Heavitas,
            fontSize = 16.sp
        )

        Text(
            text = formattedTime,
            fontSize = 100.sp,
            fontFamily = Heavitas,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = "Study and learn for your future!",
            fontFamily = Heavitas
        )

        Spacer(modifier = Modifier.height(180.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.Black)
                .padding(15.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                Button(onClick = {
                    isBreak = false
                    timeLeft = workMinutes * 60
                },
                    colors = if (isBreak == true) { ButtonDefaults.buttonColors(Color.White) } else { ButtonDefaults.buttonColors(Color.Gray) }) {
                    Text("Çalışma", color = Color.Black)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(onClick = {
                    isBreak = true
                    timeLeft = breakMinutes * 60
                },
                    colors = if (isBreak == false) { ButtonDefaults.buttonColors(Color.White) } else { ButtonDefaults.buttonColors(Color.Gray) }
                    ) {
                    Text("Mola", color = Color.Black)
                }

                Spacer(modifier = Modifier.width(12.dp))

                Button(onClick = { showSettings = true },
                    colors = ButtonDefaults.buttonColors(Color.White)) {
                    Text("Ayarlar", color = Color.Black)
                }
            }
        }
    }
}
