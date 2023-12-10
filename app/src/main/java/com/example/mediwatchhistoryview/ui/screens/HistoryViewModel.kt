package com.example.mediwatchhistoryview.ui.screens

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mediwatchhistoryview.network.Api
import kotlinx.coroutines.launch
import java.io.IOException

const val TAG = "GetData"

sealed interface HistoryUiState {
    data class Success(val data: String) : HistoryUiState
    object Error : HistoryUiState
    object Loading : HistoryUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class HistoryViewModel : ViewModel(){
    var uiState : HistoryUiState by mutableStateOf(HistoryUiState.Loading)
        private set

    init {
        getHistoryData()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun getHistoryData(){
        viewModelScope.launch {
            uiState = HistoryUiState.Loading
            uiState = try {
                val res = Api.retrofitService.getHistory()
                Log.d(TAG, "Success")
                HistoryUiState.Success(
                    res
                )

            } catch (e: IOException){
                e.message?.let { Log.d(TAG, it) }
                HistoryUiState.Error
            } catch (e: HttpException){
                e.message?.let { Log.d(TAG, it) }
                HistoryUiState.Error
            }
        }
    }
}