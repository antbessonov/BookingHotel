package org.bessonov.bookinghotel.data.network.model

import com.google.gson.annotations.SerializedName

data class BookingInfoDto(
    val id: Int,
    @SerializedName("hotel_name")
    val nameHotel: String,
    @SerializedName("hotel_adress")
    val addressHotel: String,
    @SerializedName("horating")
    val valueRating: Int,
    @SerializedName("rating_name")
    val nameRating: String,
    @SerializedName("departure")
    val departure: String,
    @SerializedName("arrival_country")
    val arrivalWithCountry: String,
    @SerializedName("tour_date_start")
    val startDateTour: String,
    @SerializedName("tour_date_stop")
    val stopDateTour: String,
    @SerializedName("number_of_nights")
    val numberNights: Int,
    @SerializedName("room")
    val numberHotel: String,
    val nutrition: String,
    @SerializedName("tour_price")
    val tourPrice: Int,
    @SerializedName("fuel_charge")
    val fuelCharge: Int,
    @SerializedName("service_charge")
    val serviceCharge: Int
)