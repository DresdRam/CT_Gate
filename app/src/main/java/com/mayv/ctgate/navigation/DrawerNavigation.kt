package com.mayv.ctgate.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mayv.ctgate.screens.attendance.AttendanceScreen
import com.mayv.ctgate.screens.count.CountScreen
import com.mayv.ctgate.screens.enters.EntersScreen
import com.mayv.ctgate.screens.exits.ExitsScreen
import com.mayv.ctgate.screens.home.HomeScreen

@Composable
fun DrawerNavigation(navigationController: NavController, paddingValues: PaddingValues) {

    NavHost(
        modifier = Modifier.padding(paddingValues = paddingValues),
        navController = navigationController as NavHostController,
        startDestination = AppScreens.HomeScreen.name
    ) {
        composable(AppScreens.AttendanceScreen.name) {
            AttendanceScreen(navigationController)
        }

        composable(AppScreens.CountScreen.name) {
            CountScreen(navigationController)
        }

        composable(AppScreens.EntersScreen.name) {
            EntersScreen(navigationController)
        }

        composable(AppScreens.ExitsScreen.name) {
            ExitsScreen(navigationController)
        }

        composable(AppScreens.HomeScreen.name) {
            HomeScreen(navigationController)
        }
    }
}