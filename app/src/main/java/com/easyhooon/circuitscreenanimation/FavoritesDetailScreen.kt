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

data class FavoritesDetailUiState(
    val eventSink: (FavoritesDetailUiEvent) -> Unit,
) : CircuitUiState

sealed interface FavoritesDetailUiEvent : CircuitUiEvent {
    data object OnButtonClick : FavoritesDetailUiEvent
}

class FavoritesDetailPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<FavoritesDetailUiState> {

    @Composable
    override fun present(): FavoritesDetailUiState {
        return FavoritesDetailUiState { event ->
            when(event) {
                is FavoritesDetailUiEvent.OnButtonClick -> navigator.pop()
            }
        }
    }

    @CircuitInject(FavoritesDetailScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): FavoritesDetailPresenter
    }
}

@CircuitInject(FavoritesDetailScreen::class, ActivityRetainedComponent::class)
@Composable
internal fun FavoritesDetail(
    state: FavoritesDetailUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("FavoritesDetail")
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { state.eventSink(FavoritesDetailUiEvent.OnButtonClick) }
        ) {
            Text("Back")
        }
    }
}