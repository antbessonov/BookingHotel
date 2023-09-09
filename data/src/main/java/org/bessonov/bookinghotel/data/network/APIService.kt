package org.bessonov.bookinghotel.data.network

import org.bessonov.bookinghotel.data.network.model.BookingInfoDto
import org.bessonov.bookinghotel.data.network.model.HotelDto
import org.bessonov.bookinghotel.data.network.model.NumberHotelListDto
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("35e0d18e-2521-4f1b-a575-f0fe366f66e3")
    suspend fun getHotel(): Response<HotelDto>

    @GET("f9a38183-6f95-43aa-853a-9c83cbb05ecd")
    suspend fun getNumberHotelList(): Response<NumberHotelListDto>

    @GET("e8868481-743f-4eb2-a0d7-2bc4012275c8")
    suspend fun getBookingInfo(): Response<BookingInfoDto>
}