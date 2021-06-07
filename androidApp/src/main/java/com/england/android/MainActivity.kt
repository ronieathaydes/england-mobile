package com.england.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.england.android.theme.EnglandTheme

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EnglandTheme {
                val navController = rememberNavController()
                val selectedItem = remember { mutableStateOf("home") }
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
                        composable(route = "home") { HomeScreen(homeViewModel) }
                        composable(route = "profile") { ProfileScreen() }
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val uiState = homeViewModel.uiStateFlow.collectAsState()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = uiState.value.content)
    }
}

@Composable
fun ProfileScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "Profile")
    }
}

@Composable
fun BottomBar(
    selectedItem: String,
    onItemSelected: (route: String) -> Unit,
) {
    BottomAppBar {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, "") },
            label = { Text(text = "Home") },
            selected = selectedItem == "home",
            onClick = { onItemSelected("home") },
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
