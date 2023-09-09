package org.bessonov.bookinghotel.model

import org.bessonov.bookinghotel.domain.model.DateTour
import org.bessonov.bookinghotel.domain.model.MainInfoHotel
import org.bessonov.bookinghotel.domain.model.Transfer

data class BookingInfoUi(
    val id: Int,
    val mainInfoHotel: MainInfoHotel,
    val rating: RatingUi,
    val transfer: Transfer,
    val dateTour: DateTour,
    val numberNights: String,
    val numberHotel: String,
    val nutrition: String,
    val price: BookingPriceUi,
)
