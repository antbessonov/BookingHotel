package org.bessonov.bookinghotel.data.network.model

import com.google.gson.annotations.SerializedName

data class NumberHotelDto(
    val id: Int,
    val name: String,
    @SerializedName("price")
    val valuePrice: Int,
    @SerializedName("price_per")
    val descriptionPrice: String,
    @SerializedName("peculiarities")
    val peculiarityList: List<String>,
    @SerializedName("image_urls")
    val imageList: List<String>
)
