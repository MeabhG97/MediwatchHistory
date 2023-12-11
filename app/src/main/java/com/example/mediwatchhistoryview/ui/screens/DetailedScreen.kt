package com.example.mediwatchhistoryview.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mediwatchhistoryview.model.History
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailedScreen(
    event: History,
    modifier: Modifier
){
    val dateTime = LocalDateTime.parse(event.dateTime)
    val dateStr = dateTime.dayOfMonth.toString() + " " +dateTime.month.toString() + " " + dateTime.year.toString()
    val timeStr = dateTime.hour.toString() + ":" + dateTime.minute.toString()
    var takenStr: String = "Yes"
    if(!event.taken){
        takenStr = "No"
    }
    
    var medListStr: String = ""
    for(med in event.medications){
        if(medListStr == ""){
            medListStr += med
            continue
        }
        medListStr += ", " + med 
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
            .padding(10.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer
            )
            .padding(top = 100.dp)
        ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Date:",
                color = MaterialTheme.colorScheme.secondary
            )
            Text(text = dateStr, color = MaterialTheme.colorScheme.secondary)
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Time:", color = MaterialTheme.colorScheme.secondary)
            Text(text = timeStr, color = MaterialTheme.colorScheme.secondary)
        }
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "Was taken:", color = MaterialTheme.colorScheme.secondary)
            Text(text = takenStr, color = MaterialTheme.colorScheme.secondary)
        }
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = "Medications", color = MaterialTheme.colorScheme.secondary)
            Text(text = medListStr, color = MaterialTheme.colorScheme.secondary)
        }
    }
}