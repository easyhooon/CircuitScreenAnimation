package com.easyhooon.circuitscreenanimation

import com.slack.circuit.runtime.screen.Screen
import kotlinx.parcelize.Parcelize

abstract class PokedexScreen(val name: String) : Screen {
    override fun toString(): String = name
}

@Parcelize
data object ListScreen : PokedexScreen(name = "List()")

@Parcelize
data object ListDetailScreen : PokedexScreen(name = "ListDetail()")

@Parcelize
data object FavoritesScreen : PokedexScreen(name = "Favorites()")

@Parcelize
data object FavoritesDetailScreen : PokedexScreen(name = "FavoritesDetail()")