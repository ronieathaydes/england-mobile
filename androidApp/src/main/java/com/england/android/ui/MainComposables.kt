package com.england.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.england.android.ui.feed.FeedScreen
import com.england.android.ui.profile.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val selectedItem: MutableState<MainMenu> = remember { mutableStateOf(MainMenu.FeedItem) }
    Scaffold(
        bottomBar = {
            BottomBar(
                selectedItem = selectedItem.value
            ) { menu ->
                navController.navigate(menu.route)
                selectedItem.value = menu
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = selectedItem.value.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = MainMenu.FeedItem.route) { FeedScreen() }
            composable(route = MainMenu.ProfileItem.route) { ProfileScreen() }
        }
    }
}

@Composable
fun BottomBar(
    selectedItem: MainMenu,
    onItemSelected: (menu: MainMenu) -> Unit,
) {
    BottomAppBar {
        BottomNavigationItem(
            icon = { Icon(imageVector = MainMenu.FeedItem.icon, contentDescription = null) },
            label = { Text(text = stringResource(id = MainMenu.FeedItem.text)) },
            selected = selectedItem == MainMenu.FeedItem,
            onClick = { onItemSelected(MainMenu.FeedItem) },
            alwaysShowLabel = false,
        )
        BottomNavigationItem(
            icon = { Icon(imageVector = MainMenu.ProfileItem.icon, contentDescription = null) },
            label = { Text(text = stringResource(id = MainMenu.ProfileItem.text)) },
            selected = selectedItem == MainMenu.ProfileItem,
            onClick = { onItemSelected(MainMenu.ProfileItem) },
            alwaysShowLabel = false,
        )
    }
}
