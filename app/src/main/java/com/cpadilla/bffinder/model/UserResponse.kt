package com.cpadilla.bffinder.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("userId") var id: Int,
    var name: String,
    var surname: String,
    var password: String,
    var socialStratum: Int,
    var address: String,
    var email: String
)
