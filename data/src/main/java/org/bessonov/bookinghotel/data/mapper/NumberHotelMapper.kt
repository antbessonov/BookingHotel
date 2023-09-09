package org.bessonov.bookinghotel.data.mapper

import org.bessonov.bookinghotel.data.network.model.NumberHotelDto
import org.bessonov.bookinghotel.data.network.model.NumberHotelListDto
import org.bessonov.bookinghotel.domain.model.NumberHotel
import org.bessonov.bookinghotel.domain.model.Price
import org.bessonov.bookinghotel.domain.util.LoadingResult
import org.bessonov.bookinghotel.domain.util.SomethingWentWrong
import retrofit2.Response

class NumberHotelMapper {

    fun mapResponseToLoadingResult(response: Response<NumberHotelListDto>): LoadingResult<List<NumberHotel>> {
        return when (response.code()) {
            200 -> {
                val responseBody = response.body() ?: throw Exception("Response body is null.")
                val numberHotelList = mapDtoListToEntityList(dtoList = responseBody.numberHotelList)
                LoadingResult.Success(numberHotelList)
            }
            else -> {
                LoadingResult.Error(message = SomethingWentWrong)
            }
        }
    }

    private fun mapDtoListToEntityList(dtoList: List<NumberHotelDto>): List<NumberHotel> {
        return dtoList.map { dto -> mapDtoToEntity(dto = dto) }
    }

    private fun mapDtoToEntity(dto: NumberHotelDto): NumberHotel {
        return NumberHotel(
            id = dto.id,
            name = dto.name,
            price = Price(
                value = dto.valuePrice,
                description = dto.descriptionPrice
            ),
            peculiarityList = dto.peculiarityList,
            imageList = dto.imageList
        )
    }
}