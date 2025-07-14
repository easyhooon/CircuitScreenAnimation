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

data class ListUiState(
    val eventSink: (ListUiEvent) -> Unit,
) : CircuitUiState

sealed interface ListUiEvent : CircuitUiEvent {
    data object OnButtonClick : ListUiEvent
}

class ListPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
) : Presenter<ListUiState> {

    @Composable
    override fun present(): ListUiState {
        return ListUiState { event ->
            when (event) {
                is ListUiEvent.OnButtonClick -> navigator.goTo(ListDetailScreen)
            }
        }
    }

    @CircuitInject(ListScreen::class, ActivityRetainedComponent::class)
    @AssistedFactory
    fun interface Factory {
        fun create(navigator: Navigator): ListPresenter
    }
}

@CircuitInject(ListScreen::class, ActivityRetainedComponent::class)
@Composable
internal fun List(
    state: ListUiState,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("List")
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { state.eventSink(ListUiEvent.OnButtonClick) }
        ) {
            Text("Navigate to ListDetail")
        }
    }
}