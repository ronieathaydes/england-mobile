package com.england.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.england.android.ui.feed.FeedScreen
import com.england.android.ui.profile.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val selectedItem = remember { mutableStateOf("feed") }
    Scaffold(
        bottomBar = {
            BottomBar(
                selectedItem = selectedItem.value
            ) { route ->
                navController.navigate(route)
                selectedItem.value = route
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = selectedItem.value,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = "feed") { FeedScreen() }
            composable(route = "profile") { ProfileScreen() }
        }
    }
}

@Composable
fun BottomBar(
    selectedItem: String,
    onItemSelected: (route: String) -> Unit,
) {
    BottomAppBar {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.List, "") },
            label = { Text(text = "Feed") },
            selected = selectedItem == "feed",
            onClick = { onItemSelected("feed") },
            alwaysShowLabel = false,
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, "") },
            label = { Text(text = "Profile") },
            selected = selectedItem == "profile",
            onClick = { onItemSelected("profile") },
            alwaysShowLabel = false,
        )
    }
}
