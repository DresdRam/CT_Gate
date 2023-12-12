package com.mayv.ctgate.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mayv.ctgate.R
import com.mayv.ctgate.components.BackButton
import com.mayv.ctgate.components.LottieAnimationView
import com.mayv.ctgate.components.SearchListItem
import com.mayv.ctgate.components.SearchListItemShimmer
import com.mayv.ctgate.navigation.AppScreens
import com.mayv.ctgate.utils.PreferenceHelper
import com.mayv.ctgate.utils.PreferenceHelper.excludeRemoved
import com.mayv.ctgate.utils.PreferenceHelper.searchSize
import com.mayv.ctgate.utils.PreferenceHelper.token

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel(),
    searchQuery: String = ""
) {

    val preferences = PreferenceHelper.getPreference(LocalContext.current)

    LaunchedEffect(key1 = true) {
        viewModel.search(
            name = searchQuery,
            excludeRemoved = preferences.excludeRemoved,
            size = preferences.searchSize,
            authToken = preferences.token!!
        )
    }

    MainSurface(navController, viewModel)
}

@Composable
private fun MainSurface(
    navController: NavController,
    viewModel: SearchViewModel
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp),
                colors = CardDefaults.cardColors(colorResource(id = R.color.primary_color))
            ) {
                BackButton(
                    modifier = Modifier.fillMaxWidth(), navController = navController
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 85.dp),
            verticalArrangement = Arrangement.Top
        ) {

            if (viewModel.isLoading) {
                SearchListItemShimmer()
                SearchListItemShimmer()
                SearchListItemShimmer()
                SearchListItemShimmer()
            } else if (viewModel.isSuccessful) {
                val data = viewModel.data.collectAsState()

                data.value.data?.let {
                    if (it.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {

                            items(items = it) { soldier ->
                                SearchListItem(
                                    name = soldier.name,
                                    nid = soldier.national_id,
                                    unit = soldier.enrollment.unit.name,
                                    holidayGroup = soldier.enrollment.holiday_group,
                                    policeNumber = soldier.enrollment.police_number,
                                    unitJob = if (soldier.enrollment.unit_job.isNullOrEmpty()) "لا يوجد تشغيل" else soldier.enrollment.unit_job
                                ) { nid ->
                                    navController.navigate(AppScreens.SoldierScreen.name + "/${nid}")
                                }

                            }
                        }
                    } else {
                        LottieAnimationView(
                            modifier = Modifier.padding(top = 30.dp),
                            message = "لا يوجد مجندين بهذا الاسم",
                            resource = R.raw.not_found_animation
                        )
                    }
                }
            } else {
                LottieAnimationView(
                    modifier = Modifier.padding(top = 30.dp),
                    message = "حدث خطأ في المعلومات",
                    resource = R.raw.error_animation
                )
            }
        }
    }
}

