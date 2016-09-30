package com.petrulak.sample.domain.mapper

import com.petrulak.sample.data.model.ApiResponse
import com.petrulak.sample.domain.model.ModelData
import com.petrulak.sample.util.extensions.toFormattedDateString
import java.util.*

class ModelDataMapper {

  fun mapToModel(input: ApiResponse): List<ModelData> {
    if (input != null && input.listValues != null) {
      return input.listValues.map { item -> ModelData(item.dateAsLong.toFormattedDateString(), item.price.toInt()) }.orEmpty()
    } else {
      return Collections.emptyList()
    }
  }

}
