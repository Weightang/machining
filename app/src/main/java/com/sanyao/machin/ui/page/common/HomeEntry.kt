package com.sanyao.machin.ui.page.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import com.sanyao.machin.ui.page.splash.SplashPage
import com.sanyao.machin.ui.theme.AppTheme


@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun HomeEntry() {
    //是否闪屏页
    var isSplash by remember { mutableStateOf(true) }
    AppTheme {
        if (isSplash) {
            SplashPage { isSplash = false }
        } else {
            AppScaffold()
        }
    }
}