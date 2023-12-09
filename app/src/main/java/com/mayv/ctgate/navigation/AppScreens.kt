package com.mayv.ctgate.navigation

import java.lang.IllegalArgumentException

enum class AppScreens {
    AttendanceScreen,
    CountScreen,
    EntersScreen,
    ExitsScreen,
    HomeScreen,
    LoginScreen,
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
            LoginScreen.name -> LoginScreen
            SearchScreen.name -> SearchScreen
            SoldierScreen.name -> SoldierScreen
            SplashScreen.name -> SplashScreen
            null -> LoginScreen
            else -> throw IllegalArgumentException("Route $route is not Recognised.")
        }
    }
}