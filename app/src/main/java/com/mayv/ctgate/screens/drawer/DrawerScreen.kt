package com.mayv.ctgate.screens.drawer

import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mayv.ctgate.R
import com.mayv.ctgate.navigation.AppScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun DrawerScreen(navController: NavController, drawerMainContent: @Composable () -> Unit) {
    MainScaffold(navController = navController, drawerMainContent= drawerMainContent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScaffold(navController: NavController, drawerMainContent: @Composable () ->Unit) {

    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            val coroutineScope = rememberCoroutineScope()

            Crossfade(
                targetState = drawerState.isOpen, animationSpec = tween(300),
                label = "Animate Menu Icon"
            ) { isOpen ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                if (drawerState.isClosed) drawerState.open() else drawerState.close()
                            }
                        },
                        modifier = Modifier
                            .padding(top = 8.dp, start = 8.dp)
                            .align(Alignment.CenterStart)
                    ) {
                        if (isOpen) {
                            Icon(
                                Icons.Rounded.ArrowBack,
                                contentDescription = "MenuButton",
                                tint = colorResource(id = R.color.gradient_color)
                            )
                        } else {
                            Icon(
                                Icons.Rounded.Menu,
                                contentDescription = "MenuButton",
                                tint = colorResource(id = R.color.gradient_color)
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->

        MainDrawerScreen(
            drawerState = drawerState,
            navController = navController,
            paddingValues = paddingValues,
            drawerMainContent = drawerMainContent
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawerScreen(
    navController: NavController,
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    paddingValues: PaddingValues,
    drawerMainContent: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerItemList: List<String> = listOf("الرئيسية", "دخول", "خروج", "تحضير", "حصر")

    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(280.dp)
                        .padding(top = 10.dp, bottom = 10.dp),
                    drawerContainerColor = Color.White
                ) {

                    Box(modifier = Modifier.fillMaxSize()) {

                        LazyColumn(
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .align(Alignment.Center),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(20.dp)
                        ) {

                            items(items = drawerItemList, itemContent = { item ->

                                when (item) {
                                    "الرئيسية" -> {
                                        DrawerItem(
                                            string = "الرئيسية",
                                            color = colorResource(id = R.color.white),
                                            coroutineScope = coroutineScope,
                                            drawerState = drawerState,
                                            navController = navController,
                                            screen = AppScreens.HomeScreen.name
                                        )
                                    }

                                    "دخول" -> {
                                        DrawerItem(
                                            string = "دخول",
                                            color = colorResource(id = R.color.green),
                                            coroutineScope = coroutineScope,
                                            drawerState = drawerState,
                                            navController = navController,
                                            screen = AppScreens.EntersScreen.name
                                        )
                                    }

                                    "خروج" -> {
                                        DrawerItem(
                                            string = "خروج",
                                            color = colorResource(id = R.color.red),
                                            coroutineScope = coroutineScope,
                                            drawerState = drawerState,
                                            navController = navController,
                                            screen = AppScreens.ExitsScreen.name
                                        )
                                    }

                                    "تحضير" -> {
                                        DrawerItem(
                                            string = "تحضير",
                                            color = Color.White,
                                            coroutineScope = coroutineScope,
                                            drawerState = drawerState,
                                            navController = navController,
                                            screen = AppScreens.AttendanceScreen.name
                                        )
                                    }

                                    "حصر" -> {
                                        DrawerItem(
                                            string = "حصر",
                                            color = Color.White,
                                            coroutineScope = coroutineScope,
                                            drawerState = drawerState,
                                            navController = navController,
                                            screen = AppScreens.CountScreen.name
                                        )
                                    }

                                    else -> {
                                        DrawerItem(
                                            string = drawerItemList[drawerItemList.size - 1],
                                            color = Color.White,
                                            coroutineScope = coroutineScope,
                                            drawerState = drawerState,
                                            navController = navController,
                                            screen = ""
                                        )
                                    }
                                }
                            })
                        }
                    }
                }
            }
        ) {
            drawerMainContent()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItem(
    string: String,
    color: Color,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavController,
    screen: String
) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .width(150.dp)
            .clickable {
                val currentRoute = navController.currentBackStackEntry?.destination?.route
                Log.d("TAG", "DrawerItem: Current Route -> $currentRoute")

                coroutineScope.launch {
                    drawerState.close()
                }

                if (currentRoute != screen) {
                    if (currentRoute != AppScreens.HomeScreen.name) {
                        navController.popBackStack()
                    }
                    navController.navigate(screen)
                }
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.drawer_item_color)),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = string,
                color = color,
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )

        }
    }
}