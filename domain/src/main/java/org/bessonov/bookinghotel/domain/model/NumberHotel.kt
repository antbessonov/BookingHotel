package org.bessonov.bookinghotel.domain.model

data class NumberHotel(
    val id: Int,
    val name: String,
    val price: Price,
    val peculiarityList: List<String>,
    val imageList: List<String>,
)
