package com.getmaji.majitest.repository

import com.getmaji.majitest.repository.test.TestData
import retrofit2.http.*

interface ApiService {
    companion object {
        const val BASE_URL = "https://api.github.com"
    }

    @GET("/")
    suspend fun getData(): TestData

}