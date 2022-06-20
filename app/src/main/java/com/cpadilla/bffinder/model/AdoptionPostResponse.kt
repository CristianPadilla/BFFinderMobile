package com.cpadilla.bffinder.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.time.LocalDateTime

data class AdoptionPostResponse(
    @SerializedName("adoptionPostId") var id: Int,
//    var date: LocalDateTime,
    var active: Boolean,
    var petId: Int,
    var pet: PetResponse,
    @SerializedName("details") var description: String
)