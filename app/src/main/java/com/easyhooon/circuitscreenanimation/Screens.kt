package com.easyhooon.circuitscreenanimation

import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

abstract class CircuitScreen(val name: String) : Screen {
    override fun toString(): String = name
}

@Parcelize
data object BottomNavigationScreen : CircuitScreen(name = "Home()")

@Parcelize
data object ListScreen : CircuitScreen(name = "List()")

@Parcelize
data object ListDetailScreen : CircuitScreen(name = "ListDetail()")

@Parcelize
data object FavoritesScreen : CircuitScreen(name = "Favorites()")

@Parcelize
data object FavoritesDetailScreen : CircuitScreen(name = "FavoritesDetail()")