package com.example.catsaplication.Networking

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("categories?count=100")
    suspend fun getCategories(): Response<List<Category>>

    @GET("category")
    suspend fun getQuestion(@Query("id") id: String): Response<Clues>
}

object RetrofitFactory {

    private const val BASE_URL = "http://jservice.io/api/"

    fun makeRetrofitService(): RetrofitService {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(RetrofitService::class.java)
    }

}