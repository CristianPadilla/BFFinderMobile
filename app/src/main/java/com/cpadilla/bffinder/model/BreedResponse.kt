package com.cpadilla.bffinder.model

import com.google.gson.annotations.SerializedName

data class BreedResponse(
    @SerializedName("breedId") var id: Int,
    @SerializedName("name") var name: String
)