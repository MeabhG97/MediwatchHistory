package com.example.mediwatchhistoryview.network

import android.util.Log
import com.example.mediwatchhistoryview.model.History
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET

const val TAG = "ApiCall"

private const val URL = "http://demo0045859.mockable.io"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(URL)
    .build()

interface ApiService{
    @GET("/history")
    suspend fun getHistory(): List<History>
}

object  Api{
    val retrofitService : ApiService by lazy {
        Log.d(TAG, "retrofit")
        retrofit.create(ApiService::class.java)
    }
}