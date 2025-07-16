package com.easyhooon.circuitscreenanimation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.easyhooon.circuitscreenanimation.BottomNavigationScreen
import com.easyhooon.circuitscreenanimation.FavoritesScreen
import com.easyhooon.circuitscreenanimation.ListScreen
import com.easyhooon.circuitscreenanimation.R
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var circuit: Circuit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CircuitCompositionLocals(circuit) {
                val backStack = rememberSaveableBackStack(root = BottomNavigationScreen)
                val navigator = rememberCircuitNavigator(backStack)

                NavigableCircuitContent(
                    navigator = navigator,
                    backStack = backStack,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}