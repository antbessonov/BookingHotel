package org.bessonov.bookinghotel.domain.model

data class BookingInfo(
    val id: Int,
    val mainInfoHotel: MainInfoHotel,
    val rating: Rating,
    val transfer: Transfer,
    val dateTour: DateTour,
    val numberNights: Int,
    val numberHotel: String,
    val nutrition: String,
    val price: BookingPrice,
)
