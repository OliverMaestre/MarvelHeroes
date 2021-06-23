package com.omaestre.data.models

import com.google.gson.annotations.SerializedName

data class ResultData(
    @SerializedName("data")
    val data: Data
)
