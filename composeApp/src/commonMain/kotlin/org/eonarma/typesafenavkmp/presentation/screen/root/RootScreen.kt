package org.eonarma.typesafenavkmp.presentation.screen.root

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.eonarma.typesafenavkmp.domain.bottomBarObjectList
import org.eonarma.typesafenavkmp.navigation.BottomBarNavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RootScreen(navToSettings: () -> Unit) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestinationRoute by remember {
        derivedStateOf { backStackEntry?.destination?.route }
    }

    LaunchedEffect(currentDestinationRoute) {
        println("CURRENT ROUTE: $currentDestinationRoute")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = currentDestinationRoute
                            ?.substringAfterLast(".")
                            ?.substringBefore("/")
                            ?.substringBefore("?")
                            ?: "Unknown"
                    )
                },
                navigationIcon = {
                    AnimatedVisibility(
                        visible = currentDestinationRoute?.contains("Details") == true
                    ) {
                        IconButton(
                            onClick = { navController.navigateUp() },
                            content = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                    contentDescription = null
                                )
                            }
                        )
                    }
                },
                actions = {
                    AnimatedVisibility(
                        visible = currentDestinationRoute?.contains("Details") == false
                    ) {
                        IconButton(
                            onClick = navToSettings
                        ) {
                            Icon(
                                imageVector =  Icons.Default.Settings,
                                contentDescription = null
                            )
                        }
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                bottomBarObjectList.forEach { bottomBarObject ->
                    NavigationBarItem(
                        selected = checkIfSelected(
                            currentDestinationRoute = currentDestinationRoute,
                            currentBottomBarItem = bottomBarObject.screen.toString()
                        ),
                        onClick = {
                            navController.navigate(bottomBarObject.screen) {
                                popUpTo(navController.graph.findStartDestination().route!!) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = bottomBarObject.icon,
                                contentDescription = bottomBarObject.content
                            )
                        }
                    )
                }
            }
        }
    ) {
        BottomBarNavGraph(
            navController = navController,
            paddingValues = it
        )
    }
}

private fun checkIfSelected(
    currentDestinationRoute: String?,
    currentBottomBarItem: String
): Boolean {
    return if ((currentDestinationRoute?.contains("Home") == true || currentDestinationRoute?.contains("Details") == true) && currentBottomBarItem == "Home") true
    else if (currentDestinationRoute?.contains(currentBottomBarItem) == true) true
    else false
}