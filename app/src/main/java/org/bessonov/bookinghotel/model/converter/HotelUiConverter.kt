package org.bessonov.bookinghotel.model.converter

import org.bessonov.bookinghotel.domain.model.Hotel
import org.bessonov.bookinghotel.model.HotelUi
import javax.inject.Inject

class HotelUiConverter @Inject constructor(
    private val priceUiConverter: PriceUiConverter,
    private val ratingUiConverter: RatingUiConverter
) {

    fun mapEntityToUiModel(entity: Hotel): HotelUi {
        return HotelUi(
            mainInfo = entity.mainInfo,
            minPrice = priceUiConverter.mapEntityToUiModel(entity = entity.minPrice),
            rating = ratingUiConverter.mapEntityToUiModel(entity = entity.rating),
            imageList = entity.imageList,
            additionalInfo = entity.additionalInfo
        )
    }
}