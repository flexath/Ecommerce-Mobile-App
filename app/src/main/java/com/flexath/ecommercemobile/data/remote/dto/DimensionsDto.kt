package com.flexath.ecommercemobile.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DimensionsDto(
    @SerializedName("depth")
    val depth: Double?,

    @SerializedName("height")
    val height: Double?,

    @SerializedName("width")
    val width: Double?
)