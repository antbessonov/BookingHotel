package org.bessonov.bookinghotel.data.repository

import org.bessonov.bookinghotel.data.mapper.NumberHotelMapper
import org.bessonov.bookinghotel.data.network.APIService
import org.bessonov.bookinghotel.domain.model.NumberHotel
import org.bessonov.bookinghotel.domain.repository.NumberHotelRepository
import org.bessonov.bookinghotel.domain.util.LoadingResult
import org.bessonov.bookinghotel.domain.util.NetworkProblem
import org.bessonov.bookinghotel.domain.util.SomethingWentWrong
import java.io.IOException

class NumberHotelRepositoryImpl(
    private val apiService: APIService,
    private val numberHotelMapper: NumberHotelMapper
) : NumberHotelRepository {

    override suspend fun getList(): LoadingResult<List<NumberHotel>> {
        return try {
            val response = apiService.getNumberHotelList()
            numberHotelMapper.mapResponseToLoadingResult(response = response)
        } catch (e: IOException) {
            LoadingResult.Error(message = NetworkProblem)
        } catch (e: Exception) {
            LoadingResult.Error(message = SomethingWentWrong)
        }
    }
}