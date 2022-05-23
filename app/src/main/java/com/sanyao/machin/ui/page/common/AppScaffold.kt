package com.sanyao.machin.ui.page.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sanyao.machin.naigation.Route
import com.sanyao.machin.ui.page.main.home.HomePage
import com.sanyao.machin.widgets.BottomNavBarView


@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun AppScaffold() {
    val navCtrl = rememberNavController()
    val navBackStackEntry by navCtrl.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        bottomBar = {
            when (currentDestination?.route) {
                Route.HOME -> BottomNavBarView(navCtrl = navCtrl)
            }

        },
        content = {
            var homeIndex = remember { 0 }
            var categoryIndex = remember { 0 }
            NavHost(
                navController = navCtrl,
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
                startDestination =Route.HOME
            ){
                composable(route = Route.HOME) {
                    HomePage(navCtrl, scaffoldState)
                }
            }

        }

    )
}