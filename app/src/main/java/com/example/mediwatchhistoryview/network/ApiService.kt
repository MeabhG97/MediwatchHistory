package com.example.mediwatchhistoryview.network

import androidx.compose.ui.layout.ScaleFactor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val URL = "http://demo0045859.mockable.io/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(URL)
    .build()

interface ApiService{
    @GET("history")
    suspend fun getHistory(): String
}

object  Api{
    val retrofitService : ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}