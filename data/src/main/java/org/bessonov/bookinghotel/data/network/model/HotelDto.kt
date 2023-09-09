package org.bessonov.bookinghotel.data.network.model

import com.google.gson.annotations.SerializedName

data class HotelDto(
    val id: Int,
    val name: String,
    @SerializedName("adress")
    val address: String,
    @SerializedName("minimal_price")
    val minPrice: Int,
    @SerializedName("price_for_it")
    val descriptionPrice: String,
    @SerializedName("rating")
    val valueRating: Int,
    @SerializedName("rating_name")
    val nameRating: String,
    @SerializedName("image_urls")
    val imageList: List<String>,
    @SerializedName("about_the_hotel")
    val additionalInfo: AdditionalInfoDto
)
