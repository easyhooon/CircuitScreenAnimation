package com.easyhooon.circuitscreenanimation.bottomnavigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.easyhooon.circuitscreenanimation.BottomNavigationScreen
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.NavigableCircuitContent
import dagger.hilt.android.components.ActivityRetainedComponent

@CircuitInject(BottomNavigationScreen::class, ActivityRetainedComponent::class)
@Composable
fun BottomNavigation(
    state: BottomNavigationUiState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(modifier = Modifier.fillMaxWidth()) {
                MainTab.entries.forEach { tab ->
                    NavigationBarItem(
                        modifier = Modifier.weight(1f),
                        selected = state.backStack.topRecord?.screen == tab.screen,
                        icon = {
                            Icon(
                                imageVector = ImageVector.vectorResource(tab.iconResId),
                                contentDescription = tab.contentDescription,
                            )
                        },
                        label = { Text(stringResource(tab.labelResId)) },
                        onClick = {
                            state.navigator.resetRoot(tab.screen, saveState = true, restoreState = true)
                        },
                    )
                }
            }
        },
    ) { innerPadding ->
        NavigableCircuitContent(
            navigator = state.navigator,
            backStack = state.backStack,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        )
    }
}
