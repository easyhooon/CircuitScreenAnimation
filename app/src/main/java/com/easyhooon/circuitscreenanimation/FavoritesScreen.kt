package com.easyhooon.circuitscreenanimation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.components.ActivityRetainedComponent

data class FavoritesUiState(
    val eventSink: (FavoritesUiEvent) -> Unit,
) : CircuitUiState

sealed interface FavoritesUiEvent : CircuitUiEvent {
    data object OnButtonClick : FavoritesUiEvent
}

class FavoritesPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<FavoritesUiState> {

    @Composable
    override fun present(): FavoritesUiState {
        return FavoritesUiState { event ->
            when(event) {
                is FavoritesUiEvent.OnButtonClick -> navigator.goTo(FavoritesDetailScreen)
            }
        }
    }

    @CircuitInject(FavoritesScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): FavoritesPresenter
    }
}

@CircuitInject(FavoritesScreen::class, ActivityRetainedComponent::class)
@Composable
internal fun Favorites(
    state: FavoritesUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Favorites")
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { state.eventSink(FavoritesUiEvent.OnButtonClick) }
        ) {
            Text("Navigate to ListDetail")
        }
    }
}