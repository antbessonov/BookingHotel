package org.bessonov.bookinghotel.data.network.model

import com.google.gson.annotations.SerializedName

data class AdditionalInfoDto(
    val description: String,
    @SerializedName("peculiarities")
    val peculiarityList: List<String>
)
