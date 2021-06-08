package com.england.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.england.android.ui.feed.FeedScreen
import com.england.android.ui.profile.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val screens = listOf(Screen.Feed, Screen.Profile)
    Scaffold(
        content = { innerPadding ->
            NavHost(navController, Screen.Feed.route, Modifier.padding(innerPadding)) {
                composable(route = Screen.Feed.route) { FeedScreen() }
                composable(route = Screen.Profile.route) { ProfileScreen() }
            }
        },
        bottomBar = {
            BottomBar(navController, screens) { screen ->
                navController.navigate(screen.route) {
                    // Pop up to the start destination of the graph to avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when re-selecting the same item
                    launchSingleTop = true
                    // Restore state when re-selecting a previously selected item
                    restoreState = true
                }
            }
        }
    )
}

@Composable
fun BottomBar(
    navController: NavHostController,
    screens: List<Screen>,
    onScreenSelected: ((Screen) -> Unit)? = null,
) {
    BottomAppBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        screens.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                label = { Text(text = stringResource(id = screen.resourceId)) },
                selected = currentDestination?.hierarchy?.any { dest -> dest.route == screen.route } == true,
                onClick = { onScreenSelected?.invoke(screen) },
                alwaysShowLabel = false,
            )
        }
    }
}
