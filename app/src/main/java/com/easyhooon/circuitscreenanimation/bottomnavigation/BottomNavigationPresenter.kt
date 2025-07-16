package com.easyhooon.circuitscreenanimation.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.easyhooon.circuitscreenanimation.BottomNavigationScreen
import com.easyhooon.circuitscreenanimation.ListScreen
import com.slack.circuit.backstack.SaveableBackStack
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

class BottomNavigationPresenter @AssistedInject constructor(
    @Assisted private val rootNavigator: Navigator,
) : Presenter<BottomNavigationUiState> {

    @Composable
    override fun present(): BottomNavigationUiState {
        val childBackStack = rememberSaveableBackStack(root = ListScreen)
        val childNavigator = rememberCircuitNavigator(childBackStack)
        val delegateNavigator = remember(childNavigator, rootNavigator) {
            DelegateNavigator(childNavigator, rootNavigator)
        }
        val currentTab = getCurrentTab(childBackStack)

        fun handleEvent(event: BottomNavigationUiEvent) {
            when (event) {
                is BottomNavigationUiEvent.OnTabSelected -> {
                    childNavigator.resetRoot(
                        newRoot = event.tab.screen,
                        saveState = true,
                        restoreState = true,
                    )
                }
            }
        }

        return BottomNavigationUiState(
            backStack = childBackStack,
            navigator = delegateNavigator,
            currentTab = currentTab,
            eventSink = ::handleEvent,
        )
    }

    @CircuitInject(BottomNavigationScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(rootNavigator: Navigator): BottomNavigationPresenter
    }
}

@Composable
fun getCurrentTab(backStack: SaveableBackStack): MainTab? {
    val currentScreen = backStack.topRecord?.screen
    return currentScreen?.let {
        MainTab.entries.find { it.screen::class == currentScreen::class }
    }
}
