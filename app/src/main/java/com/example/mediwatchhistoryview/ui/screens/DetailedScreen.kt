package com.example.mediwatchhistoryview.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mediwatchhistoryview.model.History
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailedScreen(
    event: History,
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
    Column() {
        Row {
            Text(text = "Date:")
            Text(text = dateStr)
        }
        Row {
            Text(text = "Time:")
            Text(text = timeStr)
        }
        Row{
            Text(text = "Was taken:")
            Text(text = takenStr)
        }
        Row {
            Text(text = "Medications")
            Text(text = medListStr)
        }
    }
}