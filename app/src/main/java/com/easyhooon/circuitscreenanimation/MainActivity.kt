package com.easyhooon.circuitscreenanimation

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
                val backStack = rememberSaveableBackStack(root = ListScreen)
                val navigator = rememberCircuitNavigator(backStack)

                val currentScreen = backStack.topRecord?.screen
                val shouldShowBottomBar = currentScreen is ListScreen || currentScreen is FavoritesScreen

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (shouldShowBottomBar) {
                            NavigationBar(modifier = Modifier.fillMaxWidth()) {
                                NavigationBarItem(
                                    modifier = Modifier.weight(1f),
                                    selected = backStack.topRecord?.screen is ListScreen,
                                    icon = {
                                        Icon(
                                            imageVector = ImageVector.vectorResource(R.drawable.ic_list),
                                            contentDescription = "List",
                                        )
                                    },
                                    label = { Text("List") },
                                    onClick = {
                                        navigator.resetRoot(ListScreen, saveState = true, restoreState = true)
                                    },
                                )
                                NavigationBarItem(
                                    modifier = Modifier.weight(1f),
                                    selected = backStack.topRecord?.screen is FavoritesScreen,
                                    icon = {
                                        Icon(
                                            imageVector = ImageVector.vectorResource(R.drawable.ic_list),
                                            contentDescription = "Favorites",
                                        )
                                    },
                                    label = { Text("Favorites") },
                                    onClick = {
                                        navigator.resetRoot(FavoritesScreen, saveState = true, restoreState = true)
                                    },
                                )
                            }
                        }
                    },
                ) { innerPadding ->
                    NavigableCircuitContent(
                        navigator = navigator,
                        backStack = backStack,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                    )
                }
            }
        }
    }
}