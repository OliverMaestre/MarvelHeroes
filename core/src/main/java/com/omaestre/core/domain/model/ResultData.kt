package com.omaestre.core.domain.model

import com.google.gson.annotations.SerializedName

data class ResultData(
    @SerializedName("data")
    val data: Data
)
