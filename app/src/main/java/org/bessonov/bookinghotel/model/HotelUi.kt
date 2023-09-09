package org.bessonov.bookinghotel.model

import org.bessonov.bookinghotel.domain.model.AdditionalInfoHotel
import org.bessonov.bookinghotel.domain.model.MainInfoHotel

data class HotelUi(
    val mainInfo: MainInfoHotel,
    val minPrice: PriceUi,
    val rating: RatingUi,
    val imageList: List<String>,
    val additionalInfo: AdditionalInfoHotel
)
