package org.bessonov.bookinghotel.data.mapper

import org.bessonov.bookinghotel.data.network.model.BookingInfoDto
import org.bessonov.bookinghotel.domain.model.*
import org.bessonov.bookinghotel.domain.util.LoadingResult
import org.bessonov.bookinghotel.domain.util.SomethingWentWrong
import retrofit2.Response

class BookingInfoMapper {

    fun mapResponseToLoadingResult(response: Response<BookingInfoDto>): LoadingResult<BookingInfo> {
        return when (response.code()) {
            200 -> {
                val responseBody = response.body() ?: throw Exception("Response body is null.")
                val bookingInfo = mapDtoToEntity(dto = responseBody)
                LoadingResult.Success(bookingInfo)
            }
            else -> {
                LoadingResult.Error(message = SomethingWentWrong)
            }
        }
    }

    private fun mapDtoToEntity(dto: BookingInfoDto): BookingInfo {
        return BookingInfo(
            id = dto.id,
            mainInfoHotel = MainInfoHotel(
                name = dto.nameHotel,
                address = dto.addressHotel
            ),
            rating = Rating(
                value = dto.valueRating,
                description = dto.nameRating
            ),
            transfer = Transfer(
                departure = dto.departure,
                arrival = dto.arrivalWithCountry,
            ),
            dateTour = DateTour(
                start = dto.startDateTour,
                stop = dto.stopDateTour,
            ),
            numberHotel = dto.numberHotel,
            numberNights = dto.numberNights,
            nutrition = dto.nutrition,
            price = BookingPrice(
                tourPrice = dto.tourPrice,
                fuelCharge = dto.fuelCharge,
                serviceCharge = dto.serviceCharge
            )
        )
    }
}