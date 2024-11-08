package org.eonarma.typesafenavkmp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Root: Screen()

    @Serializable
    data object BottomBarGraph: Screen()

    @Serializable
    data object Home: Screen()

    @Serializable
    data class Details(
        val id: Int = 0
    ): Screen()

    @Serializable
    data object Settings: Screen()

    @Serializable
    data object Profile: Screen()
}