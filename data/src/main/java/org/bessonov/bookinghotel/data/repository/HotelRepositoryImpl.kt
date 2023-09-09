package org.bessonov.bookinghotel.data.repository

import org.bessonov.bookinghotel.data.mapper.HotelMapper
import org.bessonov.bookinghotel.data.network.APIService
import org.bessonov.bookinghotel.domain.model.Hotel
import org.bessonov.bookinghotel.domain.repository.HotelRepository
import org.bessonov.bookinghotel.domain.util.LoadingResult
import org.bessonov.bookinghotel.domain.util.NetworkProblem
import org.bessonov.bookinghotel.domain.util.SomethingWentWrong
import java.io.IOException

class HotelRepositoryImpl(
    private val apiService: APIService,
    private val hotelMapper: HotelMapper
) : HotelRepository {

    override suspend fun get(): LoadingResult<Hotel> {
        return try {
            val response = apiService.getHotel()
            hotelMapper.mapResponseToLoadingResult(response = response)
        } catch (e: IOException) {
            LoadingResult.Error(message = NetworkProblem)
        } catch (e: Exception) {
            LoadingResult.Error(message = SomethingWentWrong)
        }
    }
}