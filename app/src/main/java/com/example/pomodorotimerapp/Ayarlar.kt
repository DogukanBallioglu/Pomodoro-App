package com.example.pomodorotimerapp

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(
    isDark: Boolean,
    onThemeChange: (Boolean) -> Unit,
    close: () -> Unit,
    onSave: (Int, Int) -> Unit
) {

    var work by remember { mutableStateOf("25") }
    var breakTime by remember { mutableStateOf("5") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Ayarlar",
            fontSize = 30.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text("Çalışma Süresi (dakika)", color = MaterialTheme.colorScheme.onBackground)
        TextField(value = work, onValueChange = { work = it })

        Spacer(modifier = Modifier.height(20.dp))

        Text("Mola Süresi (dakika)", color = MaterialTheme.colorScheme.onBackground)
        TextField(value = breakTime, onValueChange = { breakTime = it })

        Spacer(modifier = Modifier.height(30.dp))

        Text("Tema Seçimi", color = MaterialTheme.colorScheme.onBackground)

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = !isDark,
                onClick = { onThemeChange(false) }
            )
            Text("Aydınlık", color = MaterialTheme.colorScheme.onBackground)

            Spacer(modifier = Modifier.width(20.dp))

            RadioButton(
                selected = isDark,
                onClick = { onThemeChange(true) }
            )
            Text("Karanlık", color = MaterialTheme.colorScheme.onBackground)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                val w = work.toIntOrNull() ?: 25
                val b = breakTime.toIntOrNull() ?: 5
                onSave(w, b)
                close()
            },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text("Kaydet", color = MaterialTheme.colorScheme.onPrimary)
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = { close() },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text("Geri", color = MaterialTheme.colorScheme.onPrimary)
        }
    }

}
