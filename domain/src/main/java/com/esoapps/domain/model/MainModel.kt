package com.esoapps.domain.model


data class MainModel(
    val tempC: Double,
    val condition: ConditionModel,
    val humidity: Long,
    val cloud: Long,
)


data class ConditionModel(
    val text: String,
    val icon: String,
)
