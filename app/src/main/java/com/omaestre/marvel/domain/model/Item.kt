package com.omaestre.marvel.domain.model

import com.google.gson.annotations.SerializedName

data class Item  (
    @SerializedName ("resourceURI")val url : String ,
    @SerializedName ("name") val name : String
)