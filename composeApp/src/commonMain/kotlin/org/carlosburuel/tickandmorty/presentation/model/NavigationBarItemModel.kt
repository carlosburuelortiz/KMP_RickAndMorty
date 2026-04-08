package org.carlosburuel.tickandmorty.presentation.model

import androidx.compose.ui.graphics.vector.ImageVector
import org.carlosburuel.tickandmorty.presentation.navigation.Screen

data class NavigationBarItemModel(
    val icon: ImageVector,
    val label: String,
    val route: Screen,
)
