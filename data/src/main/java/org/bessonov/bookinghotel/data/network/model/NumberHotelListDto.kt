package org.bessonov.bookinghotel.data.network.model

import com.google.gson.annotations.SerializedName

data class NumberHotelListDto(
    @SerializedName("rooms")
    val numberHotelList: List<NumberHotelDto>
)
