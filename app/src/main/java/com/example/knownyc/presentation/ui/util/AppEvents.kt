package com.example.knownyc.presentation.ui.util

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import com.example.knownyc.commons.Event
import com.example.knownyc.commons.EventBus

@Composable
fun AppEvents(context: Context) {
    val lifeCycleOwner: Lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(key1 = lifeCycleOwner) {
        EventBus.events.collect{ event ->
            when (event) {
                is Event.Toast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_LONG).show()
                }

                is Event.NavigateToHomeScreen -> {/* TODO */}
            }
        }

    }
}