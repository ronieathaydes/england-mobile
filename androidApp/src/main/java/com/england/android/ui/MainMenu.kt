package com.england.android.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.england.android.R

sealed class MainMenu(
    val route: String,
    val icon: ImageVector,
    @StringRes val text: Int,
) {
    object FeedItem : MainMenu(
        route = "feed",
        icon = Icons.Filled.List,
        text = R.string.feed_menu_text,
    )

    object ProfileItem : MainMenu(
        route = "profile",
        icon = Icons.Filled.Person,
        text = R.string.profile_menu_text,
    )
}
