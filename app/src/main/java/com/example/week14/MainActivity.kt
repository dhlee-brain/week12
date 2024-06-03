package com.example.week14

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.week14.ui.theme.Week14Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Week14Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    var brmsg by remember {
        mutableStateOf("")
    }

    SystemBroadcastReceiver(
        systemAction = Intent.ACTION_POWER_CONNECTED
    ){
        val action = it?.action?:return@SystemBroadcastReceiver
        brmsg = "충전 시작"
    }

    SystemBroadcastReceiver(
        systemAction = Intent.ACTION_POWER_DISCONNECTED
    ){
        val action = it?.action?:return@SystemBroadcastReceiver
        brmsg = "충전 해제"
    }

    Column {
        Text(text = "수신 메시지")
        Text(text =brmsg)
    }
}