package com.example.mediwatchhistoryview.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.mediwatchhistoryview.R


@Composable
fun HomeScreen(
    uiState: HistoryUiState, modifier: Modifier = Modifier
){
    when(uiState){
        is HistoryUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is HistoryUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        is HistoryUiState.Success -> ResultScreen(
            uiState.data,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(
            id = R.drawable.loading_img),
            contentDescription = ""
    )
}

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
    }
}

@Composable
fun ResultScreen(
    data: String,
    modifier: Modifier = Modifier
){
    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
    ){
        Text(text = data)
    }
}
