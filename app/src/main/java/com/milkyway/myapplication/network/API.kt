package com.milkyway.myapplication.network

import com.milkyway.myapplication.model.Data
import com.milkyway.myapplication.model.PostResponse
import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("/api/users?page=2")
    fun getALLPost() : Call<PostResponse>
}