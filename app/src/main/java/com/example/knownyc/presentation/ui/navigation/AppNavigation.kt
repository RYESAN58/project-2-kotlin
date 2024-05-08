package com.example.knownyc.presentation.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.knownyc.R
import com.example.knownyc.presentation.ui.util.scaffold.AppScaffold
import com.example.knownyc.presentation.ui.util.scaffold.TitleText
import com.example.knownyc.presentation.boroughs.BoroughsScreen


@Composable
fun AppNavigationGraph() {

    val navController: NavHostController = rememberNavController()

    val isBackEnabled: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }


    //title: used by stringResource() to get scaffold topBar title
    val title: MutableIntState = remember {
        mutableIntStateOf(R.string.screen_title_home)
    }

    //titleArgs: used by stringResource() to get Scaffold topBar title
    val titleArgs: MutableState<String> = remember {
        mutableStateOf("")
    }

    val showSearchIcon: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    val searchClicked: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    val searchText: MutableState<String> = remember {
        mutableStateOf("")
    }

    navController.addOnDestinationChangedListener { _: NavController, destination: NavDestination, _: Bundle? ->
        Log.d("nav", "nav destination changed to ${destination.route}")

        isBackEnabled.value = !destination.route.equals(Routes.HOME_SCREEN)

        if (destination.route!!.startsWith(Routes.PARKS_SCREEN, startIndex = 0)) {
            title.intValue = R.string.screen_title_parks
            showSearchIcon.value = true
        } else {
            title.intValue = R.string.screen_title_home
            showSearchIcon.value = false
            searchClicked.value = false
        }
    }
    AppScaffold(
        title = {
            if (searchClicked.value) {
                // This is the TODO for SEARCHFIELD EXTRA CREDIT
                // SearchTextField(placeholder = stringResource(id = R.string.search_parks_placeholder)) { value ->
                //  searchText.value = value
                // }
            } else {
                TitleText(title = stringResource(title.intValue, titleArgs.value))
            }
        },
        showBackIcon = isBackEnabled.value,
        onBackClick = {
            if (!searchClicked.value) navController.popBackStack()
            searchClicked.value = !searchClicked.value
        },
        showSearchIcon = showSearchIcon.value && !searchClicked.value,
        onActionClick = {
            searchClicked.value = true
        },
    ) { paddingValues: PaddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {
                composable(Routes.HOME_SCREEN) {
                    BoroughsScreen(onBoroughClicked = { borough, title ->
                        titleArgs.value = title
                        navController.navigate(Routes.PARKS_SCREEN + "?borough=$borough")
                    })
                }
                composable(
                    Routes.PARKS_SCREEN + "?borough={borough}",
                    arguments = listOf(
                        navArgument("borough") {
                            type = NavType.StringType
                        },
                    ),
                ) { backStackEntry: NavBackStackEntry ->
                    // TODO: PROJECT 2 add PARKSCREEN HERE
                   //NycParksScreen(
                      //  boroughCode = backStackEntry.arguments?.getString("borough")!!,
                       // searchText = searchText.value
                    //)
                }
            }
        }
    }
}

