package com.example.mediwatchhistoryview.ui.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mediwatchhistoryview.R
import com.example.mediwatchhistoryview.model.History
import java.time.LocalDateTime


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    uiState: HistoryUiState,
    modifier: Modifier = Modifier,
    onNextClick: (History) -> Unit
){
    when(uiState){
        is HistoryUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is HistoryUiState.Error -> ErrorScreen(modifier = modifier.fillMaxSize())
        is HistoryUiState.Success -> ResultScreen(
            uiState.data,
            modifier = modifier.fillMaxSize(),
            onNextClick = onNextClick
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ResultScreen(
    data: List<History>,
    modifier: Modifier = Modifier,
    onNextClick: (History) -> Unit
){
    Log.d("Entries", data.toString())
    val entriesByDay: MutableList<MutableList<History>> = MutableList(data.size) { mutableListOf<History>()}
    var prevDay = 0
    var i = 0
    for(entry in data){
        val day = LocalDateTime.parse(entry.dateTime).dayOfMonth
        if(day == prevDay){
            entriesByDay[i].add(entry)
        } else {
            Log.d("Entries by day", entriesByDay.toString())
            i++
            entriesByDay[i].add(entry)
            prevDay = day
        }
    }
    Log.d("Entries", entriesByDay.toString())
    
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){
        items(entriesByDay){
            row ->
                if(row.isNotEmpty()) {
                    HistroyRow(
                        row = row,
                        onNextClick = onNextClick
                    )
                }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistroyRow(
    row: MutableList<History>,
    modifier: Modifier = Modifier,
    onNextClick: (History) -> Unit
){
    val dateTime = LocalDateTime.parse(row[0].dateTime)
    val dateStr = dateTime.dayOfMonth.toString() + " " +dateTime.month.toString() + " " + dateTime.year.toString()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp, vertical = 2.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer
            )
            .padding(horizontal = 5.dp, vertical = 2.dp)
            .padding(bottom = 5.dp)
    ){
        Text(
            text = dateStr,
            color = MaterialTheme.colorScheme.secondary,
            modifier = modifier
                .padding(bottom = 5.dp)
        )
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = modifier
                .fillMaxWidth()
        ){
            for(entry in row){
                HistoryEntry(
                    entry = entry,
                    onNextClick = onNextClick
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryEntry(
    entry: History,
    modifier: Modifier = Modifier,
    onNextClick: (History) -> Unit
){
    var backgroundColour = colorResource(id = R.color.celadon)
    if(!entry.taken){
        backgroundColour = colorResource(id = R.color.dogwood_rose)
    }
    
    Button(
        onClick =   {onNextClick(entry)},
        modifier = modifier
            .size(100.dp),
        colors = ButtonDefaults.buttonColors(backgroundColour),
        shape = RoundedCornerShape(20.dp)

    ) {
        
    }
}
