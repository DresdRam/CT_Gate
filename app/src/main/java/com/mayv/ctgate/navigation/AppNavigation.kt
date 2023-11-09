package com.mayv.ctgate.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mayv.ctgate.screens.attendance.AttendanceScreen
import com.mayv.ctgate.screens.count.CountScreen
import com.mayv.ctgate.screens.enters.EntersScreen
import com.mayv.ctgate.screens.exits.ExitsScreen
import com.mayv.ctgate.screens.home.HomeScreen
import com.mayv.ctgate.screens.search.SearchScreen
import com.mayv.ctgate.screens.soldier.SoldierScreen
import com.mayv.ctgate.screens.soldier.SoldierViewModel
import com.mayv.ctgate.screens.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    val transitionSpeed = 300

    NavHost(navController = navigationController, startDestination = AppScreens.SplashScreen.name) {

        composable(
            route = AppScreens.SearchScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            SearchScreen(navigationController)
        }

        composable(
            route = AppScreens.SoldierScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {

            val viewModel = hiltViewModel<SoldierViewModel>()
            SoldierScreen(navigationController, viewModel)
        }

        composable(
            route = AppScreens.SplashScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            SplashScreen(navigationController)
        }

        composable(AppScreens.AttendanceScreen.name) {
            AttendanceScreen(mainNavController = navigationController)
        }

        composable(AppScreens.CountScreen.name) {
            CountScreen(mainNavController = navigationController)
        }

        composable(AppScreens.EntersScreen.name) {
            EntersScreen(mainNavController = navigationController)
        }

        composable(AppScreens.ExitsScreen.name) {
            ExitsScreen(mainNavController = navigationController)
        }

        composable(AppScreens.HomeScreen.name) {
            HomeScreen(mainNavController = navigationController)
        }
    }
}