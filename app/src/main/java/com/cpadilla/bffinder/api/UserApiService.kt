package com.cpadilla.bffinder.api

import com.cpadilla.bffinder.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface UserApiService {


    @GET
    fun getUser(@Url url: String): Call<UserResponse>
}