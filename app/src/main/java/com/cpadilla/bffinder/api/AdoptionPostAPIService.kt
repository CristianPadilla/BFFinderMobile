package com.cpadilla.bffinder.api

import com.cpadilla.bffinder.model.AdoptionPostResponse
import com.cpadilla.bffinder.model.PetResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface AdoptionPostAPIService {

    //@GET("/pets/all")
    //suspend fun getAllPets(@Path("breed") breed: String): Response<DogResponse>
    @GET
    fun getPosts(@Url url: String): Call<List<AdoptionPostResponse>>
    @GET
    fun getPost(@Url url: String): Call<AdoptionPostResponse>

}