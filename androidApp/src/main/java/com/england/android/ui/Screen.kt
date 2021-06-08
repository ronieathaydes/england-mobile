package com.england.android.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.england.android.R

sealed class Screen(val route: String, val icon: ImageVector, @StringRes val resourceId: Int) {
    object Feed : Screen(route = "feed", icon = Icons.Filled.List, resourceId = R.string.feed_menu_text)
    object Profile : Screen(route = "profile", icon = Icons.Filled.Person, resourceId = R.string.profile_menu_text)
}
