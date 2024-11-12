package com.workdev.data.remote


import com.workdev.domain.entity.aa.aa

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {



    @GET("public/comics")
    suspend fun Comics(
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String,
        @Query("offset") offset:Int
    ): aa


    @GET("public/comics/{id}")
    suspend fun ComicsDetails(
        @Path("id") id:Int,
        @Query("ts") ts: String,
        @Query("apikey") apikey: String,
        @Query("hash") hash: String

    ): aa

}