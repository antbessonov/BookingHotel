package org.bessonov.bookinghotel.domain.model

data class Hotel(
    val id: Int,
    val mainInfo: MainInfoHotel,
    val minPrice: Price,
    val rating: Rating,
    val imageList: List<String>,
    val additionalInfo: AdditionalInfoHotel
)