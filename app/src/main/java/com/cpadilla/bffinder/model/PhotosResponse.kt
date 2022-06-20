package com.cpadilla.bffinder.model

import com.google.gson.annotations.SerializedName

data class PhotosResponse(var id: Int, @SerializedName("src") var source: String, var petId: Int)
