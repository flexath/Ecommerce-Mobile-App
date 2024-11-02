package com.flexath.ecommercemobile.data.remote.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class MetaDto(
    @SerializedName("barcode")
    val barcode: String?,

    @SerializedName("createdAt")
    val createdAt: String?,

    @SerializedName("qrCode")
    val qrCode: String?,

    @SerializedName("updatedAt")
    val updatedAt: String?
)