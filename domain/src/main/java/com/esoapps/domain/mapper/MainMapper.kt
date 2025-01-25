package com.esoapps.domain.mapper

import com.esoapps.domain.entity.ApiData

import com.esoapps.domain.ext.Mapper
import com.esoapps.domain.model.ConditionModel
import com.esoapps.domain.model.MainModel

import javax.inject.Inject

class MainMapper @Inject constructor() : Mapper<ApiData?, MainModel> {
    override fun mapFrom(from: ApiData?): MainModel {
        return MainModel(
            tempC = from?.tempC ?: 0.0,
            condition = ConditionModel(
                text = from?.condition?.text ?: "",
                icon = from?.condition?.icon ?: ""
            ),
            humidity = from?.humidity ?: 0,
            cloud = from?.cloud ?: 0,

            )
    }
}