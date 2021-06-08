package com.england.android.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(
        selectedItem = MainMenu.FeedItem,
        onItemSelected = {},
    )
}
