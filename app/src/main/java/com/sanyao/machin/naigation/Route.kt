package com.sanyao.machin.naigation

import FaIcons.File
import FaIcons.Home
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.guru.fontawesomecomposelib.FaIcon
import com.sanyao.machin.R

object Route {
    const val HOME = "home"
    const val CATEGORY = "category"
    const val COLLECTION = "collection"
    const val PROFILE = "profile"
    const val WEB_VIEW = "web_view"
    const val LOGIN = "login"
    const val ARTICLE_SEARCH = "article_search"


}

sealed class BottomNavRoute(
    var routeName: String,
    @StringRes var stringId: Int,
    var icon: ImageVector
) {

    object Home: BottomNavRoute(Route.HOME, R.string.home, Icons.Default.Home)
    object Category: BottomNavRoute(Route.CATEGORY, R.string.category, Icons.Default.Menu)
    object Collection: BottomNavRoute(Route.COLLECTION, R.string.collection, Icons.Default.Favorite)
    object Profile: BottomNavRoute(Route.PROFILE, R.string.profile, Icons.Default.Person)
}