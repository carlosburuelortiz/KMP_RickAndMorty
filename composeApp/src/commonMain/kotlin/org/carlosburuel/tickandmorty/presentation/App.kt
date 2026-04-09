package org.carlosburuel.tickandmorty.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import org.carlosburuel.tickandmorty.presentation.model.NavigationBarItemModel
import org.carlosburuel.tickandmorty.presentation.navigation.Screen
import org.carlosburuel.tickandmorty.presentation.screen.EpisodeScreen
import org.carlosburuel.tickandmorty.presentation.screen.LocationScreen
import org.carlosburuel.tickandmorty.presentation.screen.character.CharacterScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(KtorNetworkFetcherFactory())
                }
                .build()
        }

        val items = listOf(
            NavigationBarItemModel(
                icon = Icons.Default.Person,
                label = "Personajes",
                route = Screen.Characters,
            ),
            NavigationBarItemModel(
                icon = Icons.Default.LocationOn,
                label = "Lugares",
                route = Screen.Locations,
            ),
            NavigationBarItemModel(
                icon = Icons.Default.Movie,
                label = "Episodias",
                route = Screen.Episodes,
            ),
        )

        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.White,
            bottomBar = {
                NavigationBar {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.hasRoute(item.route::class) } == true,
                            label = { Text(item.label) },
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    item.icon,
                                    contentDescription = null,
                                )
                            }
                        )
                    }
                }
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Characters,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable<Screen.Characters> {
                    CharacterScreen()
                }
                composable<Screen.Locations> {
                    LocationScreen()
                }
                composable<Screen.Episodes> {
                    EpisodeScreen()
                }
            }
        }
    }
}
