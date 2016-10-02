package com.petrulak.sample.domain.mapper

import com.petrulak.sample.data.model.ApiResponse
import com.petrulak.sample.domain.model.ModelData
import com.petrulak.sample.util.extensions.toFormattedDateString

class ModelDataMapper {

  fun mapToModel(input: ApiResponse): List<ModelData> {
    return input.listValues.map { i -> ModelData(i.dateAsLong.toFormattedDateString(), i.price.toInt()) }.orEmpty()
  }

}
