package com.example.week14

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext

@Composable
fun SystemBroadcastReceiver(
    systemAction: String,
    onSystemEvent: (intent: Intent?) -> Unit
) {
    val context = LocalContext.current
    val currentOnSystemEvent by rememberUpdatedState(newValue = onSystemEvent)
    
    DisposableEffect(context, systemAction) {
        val intentFilter = IntentFilter(systemAction)
        val broadcast = object:BroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                intent?.action?.let { action-> Log.i("intent", action) }
                currentOnSystemEvent(intent)
            }
        }
        context.registerReceiver(broadcast, intentFilter)
        onDispose {
            context.unregisterReceiver(broadcast)
        }
    }
}