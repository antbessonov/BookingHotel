package org.bessonov.bookinghotel.data.mapper

import org.bessonov.bookinghotel.data.network.model.HotelDto
import org.bessonov.bookinghotel.domain.model.*
import org.bessonov.bookinghotel.domain.util.LoadingResult
import org.bessonov.bookinghotel.domain.util.SomethingWentWrong
import retrofit2.Response

class HotelMapper {

    fun mapResponseToLoadingResult(response: Response<HotelDto>): LoadingResult<Hotel> {
        return when (response.code()) {
            200 -> {
                val responseBody = response.body() ?: throw Exception("Response body is null.")
                val hotel = mapDtoToEntity(dto = responseBody)
                LoadingResult.Success(hotel)
            }
            else -> {
                LoadingResult.Error(message = SomethingWentWrong)
            }
        }
    }

    private fun mapDtoToEntity(dto: HotelDto): Hotel {
        return Hotel(
            id = dto.id,
            mainInfo = MainInfoHotel(
                name = dto.name,
                address = dto.address,
            ),
            minPrice = Price(
                value = dto.minPrice,
                description = dto.descriptionPrice
            ),
            rating = Rating(
                value = dto.valueRating,
                description = dto.nameRating
            ),
            imageList = dto.imageList,
            additionalInfo = AdditionalInfoHotel(
                description = dto.additionalInfo.description,
                peculiarityList = dto.additionalInfo.peculiarityList
            )
        )
    }
}