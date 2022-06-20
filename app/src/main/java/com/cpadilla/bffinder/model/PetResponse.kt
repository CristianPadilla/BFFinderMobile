package com.cpadilla.bffinder.model

import com.cpadilla.bffinder.model.PhotosResponse
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class PetResponse(
    @SerializedName("petId") var id: Int,
    @SerializedName("name") var name: String,
    var weight: Int,
    var age: Int,
    var vaccinated: Boolean,
    var dangerous: Boolean,
    var size: Char,
    var sex: String,
    var sterilized: Boolean,
    @SerializedName("active") var status: Boolean,
    var breedId: Int,
    var ownerId: Int,
    var photos: List<PhotosResponse>,
    var breed: BreedResponse,
    @SerializedName("owner") var owner: UserResponse
)