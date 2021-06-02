package com.omaestre.marvel.domain.model

import com.google.gson.annotations.SerializedName

data class ServiceResponse (

    @SerializedName ("code")
    val code : Int,

    @SerializedName ("status")
    val status : String,

    @SerializedName ("data")
    val data : Data
)