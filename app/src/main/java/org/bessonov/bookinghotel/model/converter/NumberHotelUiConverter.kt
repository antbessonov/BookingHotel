package org.bessonov.bookinghotel.model.converter

import org.bessonov.bookinghotel.domain.model.NumberHotel
import org.bessonov.bookinghotel.model.NumberHotelUi
import javax.inject.Inject

class NumberHotelUiConverter @Inject constructor(
    private val priceUiConverter: PriceUiConverter,
) {

    fun mapEntityListToUiModelList(entityList: List<NumberHotel>): List<NumberHotelUi> {
        return entityList.map { entity ->
            mapEntityToUiModel(entity = entity)
        }
    }

    private fun mapEntityToUiModel(entity: NumberHotel): NumberHotelUi {
        return NumberHotelUi(
            id = entity.id,
            name = entity.name,
            price = priceUiConverter.mapEntityToUiModel(entity = entity.price),
            peculiarityList = entity.peculiarityList,
            imageList = entity.imageList
        )
    }
}