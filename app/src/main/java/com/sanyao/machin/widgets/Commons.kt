package com.sanyao.machin.widgets

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.sanyao.machin.naigation.BottomNavRoute
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text

import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination

import androidx.navigation.compose.currentBackStackEntryAsState
import com.sanyao.machin.ui.theme.AppTheme

@Composable
fun BottomNavBarView(navCtrl: NavHostController) {
    val bottomNavList = listOf(
        BottomNavRoute.Home,
        BottomNavRoute.Category,
        BottomNavRoute.Collection,
        BottomNavRoute.Profile
    )
    BottomNavigation {
        val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        bottomNavList.forEach { screen ->
            BottomNavigationItem(
                modifier = Modifier.background(AppTheme.colors.themeUi),
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null
                    )
                },
                label = { Text(text = stringResource(screen.stringId)) },
                selected = currentDestination?.hierarchy?.any {
                    it.route == screen.routeName
                }==true,
                onClick = {
                    println("BottomNavView当前路由 ===> ${currentDestination?.hierarchy?.toList()}")
                    println("当前路由栈 ===> ${navCtrl.graph.nodes}")
                    if (currentDestination?.route != screen.routeName) {
                        navCtrl.navigate(screen.routeName) {
                            popUpTo(navCtrl.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                })

        }

    }


}