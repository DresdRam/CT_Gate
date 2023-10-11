package com.mayv.ctgate.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mayv.ctgate.screens.drawer.DrawerScreen
import com.mayv.ctgate.screens.search.SearchScreen
import com.mayv.ctgate.screens.soldier.SoldierScreen
import com.mayv.ctgate.screens.splash.SplashScreen

@Composable
fun AppNavigation(){
    val navigationController = rememberNavController()

    NavHost(navController = navigationController, startDestination = AppScreens.SplashScreen.name){

        composable(AppScreens.SearchScreen.name){
            SearchScreen(navigationController)
        }

        composable(AppScreens.SoldierScreen.name){
            SoldierScreen(navigationController)
        }

        composable(AppScreens.SplashScreen.name){
            SplashScreen(navigationController)
        }

        composable(AppScreens.DrawerScreen.name){
            DrawerScreen(navigationController)
        }
    }
}