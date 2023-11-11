package com.mayv.ctgate.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
    AttendanceScreen,
    CountScreen,
    EntersScreen,
    ExitsScreen,
    HomeScreen,
    SearchScreen,
    SoldierScreen,
    SplashScreen;

    companion object {
        fun fromRoute(route: String): AppScreens
        = when(route?.substringBefore('/')){
            AttendanceScreen.name -> AttendanceScreen
            CountScreen.name -> CountScreen
            EntersScreen.name -> EntersScreen
            ExitsScreen.name -> ExitsScreen
            HomeScreen.name -> HomeScreen
            SearchScreen.name -> SearchScreen
            SoldierScreen.name -> SoldierScreen
            SplashScreen.name -> SplashScreen
            null -> SplashScreen
            else -> throw IllegalArgumentException("Route $route is not Recognised.")
        }
    }
}