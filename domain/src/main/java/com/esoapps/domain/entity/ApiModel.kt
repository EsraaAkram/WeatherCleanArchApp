package com.esoapps.domain.entity

import com.google.gson.annotations.SerializedName

data class ApiModel(
    @SerializedName("current")
    val apiData: ApiData? = null,
)

data class ApiData(
    @SerializedName("temp_c")
    val tempC: Double,
    val condition: Condition,
    val humidity: Long,
    val cloud: Long,
    )


data class Condition(
    val text: String?,
    val icon: String?,
)


