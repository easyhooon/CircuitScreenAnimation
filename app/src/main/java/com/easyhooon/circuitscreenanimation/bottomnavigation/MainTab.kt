package com.easyhooon.circuitscreenanimation.bottomnavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.easyhooon.circuitscreenanimation.FavoritesScreen
import com.easyhooon.circuitscreenanimation.ListScreen
import com.easyhooon.circuitscreenanimation.R
import com.slack.circuit.runtime.screen.Screen

enum class MainTab(
    @DrawableRes val iconResId: Int,
    @StringRes val labelResId: Int,
    internal val contentDescription: String,
    val screen: Screen,
) {
    LIST(
        iconResId = R.drawable.ic_list,
        labelResId = R.string.list_label,
        contentDescription = "List Icon",
        screen = ListScreen,
    ),
    FAVORITES(
        iconResId = R.drawable.ic_favorites,
        labelResId = R.string.favorites_label,
        contentDescription = "Favorites Icon",
        screen = FavoritesScreen,
    ),
}
