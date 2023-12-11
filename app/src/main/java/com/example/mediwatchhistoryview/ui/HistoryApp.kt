package com.example.mediwatchhistoryview.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mediwatchhistoryview.model.History
import com.example.mediwatchhistoryview.ui.screens.DetailedScreen
import com.example.mediwatchhistoryview.ui.screens.HistoryViewModel
import com.example.mediwatchhistoryview.ui.screens.HomeScreen
import com.example.mediwatchhistoryview.ui.screens.detailEvent

enum class HistoryScreens(){
    Home,
    Detailed
}

@RequiresApi(Build.VERSION_CODES.O)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(
    viewModel: HistoryViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
){
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
            .nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = { TopBar(scrollBehaviour = scrollBehaviour)}
    ) {
        val histroyViewModel : HistoryViewModel = viewModel()
        var event: History
        NavHost(
            navController = navController,
            startDestination = HistoryScreens.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ){
            composable(route = HistoryScreens.Home.name){
                HomeScreen(
                    uiState = histroyViewModel.uiState,
                    onNextClick = {
                        histroyViewModel.setEvent(it)
                        navController.navigate(HistoryScreens.Detailed.name)
                    }
                )
            }
            composable(route = HistoryScreens.Detailed.name){
                DetailedScreen(
                    event = detailEvent,
                    modifier = Modifier
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(scrollBehaviour : TopAppBarScrollBehavior, modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
                Text(text = "Mediwatch History View")
        },
        scrollBehavior = scrollBehaviour,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        )
    )
}
