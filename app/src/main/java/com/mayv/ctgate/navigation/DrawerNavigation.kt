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

    // CODE_REVIEW: It's not correct to have 2 nav hosts inside the same app, you should have only one nav host which includes all your screen
    // and then you can implement the drawer at the highest level of the nav host, and you just hide it on splash screen, and show it for the other screens.
    // another solution is to have the drawer logic over the home screen, look to the branch that i created with an example of that "branch= refactor_navigation_structure"
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