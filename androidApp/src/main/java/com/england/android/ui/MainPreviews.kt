package com.england.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Preview
@Composable
fun BottomBarPreview() {
    val navController = rememberNavController()
    val screens = listOf(Screen.Feed, Screen.Profile)
    BottomBar(navController, screens)
}
