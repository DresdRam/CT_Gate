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
fun DrawerNavigation(mainNavController: NavController, drawerNavController: NavController, paddingValues: PaddingValues) {

    NavHost(
        modifier = Modifier.padding(paddingValues = paddingValues),
        navController = drawerNavController as NavHostController,
        startDestination = AppScreens.HomeScreen.name
    ) {
        composable(AppScreens.AttendanceScreen.name) {
            AttendanceScreen(mainNavController = mainNavController, drawerNavController = drawerNavController)
        }

        composable(AppScreens.CountScreen.name) {
            CountScreen(mainNavController = mainNavController, drawerNavController = drawerNavController)
        }

        composable(AppScreens.EntersScreen.name) {
            EntersScreen(mainNavController = mainNavController, drawerNavController = drawerNavController)
        }

        composable(AppScreens.ExitsScreen.name) {
            ExitsScreen(mainNavController = mainNavController, drawerNavController = drawerNavController)
        }

        composable(AppScreens.HomeScreen.name) {
            HomeScreen(mainNavController = mainNavController, drawerNavController = drawerNavController)
        }
    }
}