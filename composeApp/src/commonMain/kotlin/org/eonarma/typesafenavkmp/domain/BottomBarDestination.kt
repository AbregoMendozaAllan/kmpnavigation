package org.eonarma.typesafenavkmp.domain

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import org.eonarma.typesafenavkmp.navigation.Screen

sealed class BottomBarDestination(
    val screen: Screen,
    val icon: ImageVector,
    val content: String? = null
    ) {
    data object Home: BottomBarDestination(
        screen = Screen.Home,
        icon = Icons.Default.Home,
        content = Screen.Home.toString()
    )
    data object Profile: BottomBarDestination(
        screen = Screen.Profile,
        icon = Icons.Default.Person,
        content = Screen.Profile.toString()
    )
}

val bottomBarObjectList = listOf<BottomBarDestination>(
    BottomBarDestination.Home,
    BottomBarDestination.Profile
)