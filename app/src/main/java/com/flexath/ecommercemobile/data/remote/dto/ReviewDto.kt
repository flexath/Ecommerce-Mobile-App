package com.flexath.ecommercemobile.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ReviewDto(
    @SerializedName("comment")
    val comment: String?,

    @SerializedName("date")
    val date: String?,

    @SerializedName("rating")
    val rating: Int?,

    @SerializedName("reviewerEmail")
    val reviewerEmail: String?,

    @SerializedName("reviewerName")
    val reviewerName: String?
)