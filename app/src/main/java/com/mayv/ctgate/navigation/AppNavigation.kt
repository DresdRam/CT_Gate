package com.mayv.ctgate.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mayv.ctgate.screens.attendance.AttendanceScreen
import com.mayv.ctgate.screens.count.CountScreen
import com.mayv.ctgate.screens.enters.EntersScreen
import com.mayv.ctgate.screens.exits.ExitsScreen
import com.mayv.ctgate.screens.home.HomeScreen
import com.mayv.ctgate.screens.login.LoginScreen
import com.mayv.ctgate.screens.search.SearchScreen
import com.mayv.ctgate.screens.settings.SettingsScreen
import com.mayv.ctgate.screens.soldier.SoldierScreen
import com.mayv.ctgate.screens.splash.SplashScreen

@Composable
fun AppNavigation() {
    val navigationController = rememberNavController()
    val transitionSpeed = 300

    NavHost(navController = navigationController, startDestination = AppScreens.SplashScreen.name) {

        composable(route = AppScreens.SearchScreen.name.plus("/{search_name}"),
            arguments = listOf(
                navArgument("search_name"){ type = NavType.StringType }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            val searchName = it.arguments?.getString("search_name") ?: ""
            SearchScreen(navigationController, searchQuery = searchName)
        }

        composable(route = AppScreens.SoldierScreen.name.plus("/{national_id}"),
            arguments = listOf(
                navArgument("national_id"){ type = NavType.LongType }
            ),
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            val nid = it.arguments?.getLong("national_id") ?: 0
            SoldierScreen(navigationController, nid)
        }

        composable(route = AppScreens.SplashScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            SplashScreen(navigationController)
        }

        composable(AppScreens.AttendanceScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            AttendanceScreen(navigationController = navigationController)
        }

        composable(AppScreens.CountScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            CountScreen(navigationController = navigationController)
        }

        composable(AppScreens.EntersScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            EntersScreen(navigationController = navigationController)
        }

        composable(AppScreens.ExitsScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            ExitsScreen(navigationController = navigationController)
        }

        composable(AppScreens.HomeScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            HomeScreen(navigationController = navigationController)
        }

        composable(AppScreens.LoginScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            LoginScreen(navigationController = navigationController)
        }

        composable(AppScreens.SettingsScreen.name,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(transitionSpeed)
                )
            }, popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }, popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(transitionSpeed)
                )
            }) {
            SettingsScreen(navigationController = navigationController)
        }
    }
}