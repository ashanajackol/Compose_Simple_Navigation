package com.ashana.compose.navigation.dummy

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

enum class Screens(@StringRes val title: Int) {
    ENTER_NAME(title = R.string.enter_detail_name),
    ENTER_PERSONAL_DATA(title = R.string.enter_detail_personal_data),
    VIEW_SUMMARY(title = R.string.view_summary)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenHolderScreen(
    modifier: Modifier = Modifier,
    personalDetailViewModel: PersonalDetailViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Screens.valueOf(
        backStackEntry?.destination?.route ?: Screens.ENTER_NAME.name
    )

    Scaffold(
        topBar = {
            TopNavigationBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigationUp = { navController.navigateUp() },
                modifier = modifier
            )
        }
    ) { innerPadding ->

        val viewModelState by personalDetailViewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Screens.ENTER_NAME.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = Screens.ENTER_NAME.name) {
                EnterDetailNameScreen(
                    onNextButtonClick = {
                        personalDetailViewModel.addName(it)
                        navController.navigate(Screens.ENTER_PERSONAL_DATA.name)
                    }
                )
            }
            composable(route = Screens.ENTER_PERSONAL_DATA.name) {
                EnterDetailPersonalDataScreen(
                    onNextButtonClick = {
                        personalDetailViewModel.addAddress(it)
                        navController.navigate(Screens.VIEW_SUMMARY.name)
                    }
                )
            }
            composable(route = Screens.VIEW_SUMMARY.name) {
                val ctx = LocalContext.current
                ViewSummary(
                    fullName = viewModelState.name,
                    onButtonClick = {
                        Toast.makeText(ctx, "The end", Toast.LENGTH_SHORT).show()
                        navController.popBackStack(Screens.ENTER_NAME.name, false)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopNavigationBar(
    currentScreen: Screens,
    canNavigateBack: Boolean,
    navigationUp: () -> Unit,
    modifier: Modifier
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick =  navigationUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    )
}