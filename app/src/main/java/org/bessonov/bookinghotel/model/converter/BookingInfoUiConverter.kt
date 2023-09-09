package org.bessonov.bookinghotel.model.converter

import org.bessonov.bookinghotel.domain.model.BookingInfo
import org.bessonov.bookinghotel.model.BookingInfoUi
import org.bessonov.bookinghotel.util.getNightRuAddition
import javax.inject.Inject

class BookingInfoUiConverter @Inject constructor(
    private val ratingUiConverter: RatingUiConverter,
    private val bookingPriceUiConverter: BookingPriceUiConverter
) {

    fun mapEntityToUiModel(entity: BookingInfo): BookingInfoUi {
        return BookingInfoUi(
            id = entity.id,
            mainInfoHotel = entity.mainInfoHotel,
            rating = ratingUiConverter.mapEntityToUiModel(entity = entity.rating),
            transfer = entity.transfer,
            dateTour = entity.dateTour,
            numberNights = entity.numberNights.getNightRuAddition(),
            numberHotel = entity.numberHotel,
            nutrition = entity.nutrition,
            price = bookingPriceUiConverter.mapEntityToUiModel(entity = entity.price)
        )
    }
}
