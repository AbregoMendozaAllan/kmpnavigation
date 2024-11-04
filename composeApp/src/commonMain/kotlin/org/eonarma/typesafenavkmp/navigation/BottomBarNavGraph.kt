package org.eonarma.typesafenavkmp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import org.eonarma.typesafenavkmp.presentation.screen.root.bottom_bar.home.details.DetailsScreen
import org.eonarma.typesafenavkmp.presentation.screen.root.bottom_bar.home.HomeScreen
import org.eonarma.typesafenavkmp.presentation.screen.root.bottom_bar.profile.ProfileScreen
import kotlin.random.Random

@Composable
fun BottomBarNavGraph(
    navController: NavHostController,
    paddingValues: PaddingValues
) {
    NavHost(
        modifier = Modifier
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            ),
        navController = navController,
        startDestination = Screen.BottomBarGraph
    ) {
        navigation<Screen.BottomBarGraph>(
            startDestination = Screen.Home
        ) {
            composable<Screen.Home> {
                HomeScreen(
                    navigationToDetails = {
                        navController.navigate(
                            Screen.Details(
                                id = Random.nextInt(from = 0, until = 100)
                            )
                        )
                    }
                )
            }
            composable<Screen.Details> {
                val id = it.toRoute<Screen.Details>().id
                DetailsScreen(id = id)
            }
        }
        composable<Screen.Profile> {
            ProfileScreen(

            )
        }
    }
}