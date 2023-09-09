package org.bessonov.bookinghotel.model

data class NumberHotelUi(
    val id: Int,
    val name: String,
    val price: PriceUi,
    val peculiarityList: List<String>,
    val imageList: List<String>,
)
