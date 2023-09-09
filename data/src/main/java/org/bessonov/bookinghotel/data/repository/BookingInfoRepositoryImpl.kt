package org.bessonov.bookinghotel.data.repository

import org.bessonov.bookinghotel.data.mapper.BookingInfoMapper
import org.bessonov.bookinghotel.data.network.APIService
import org.bessonov.bookinghotel.domain.model.BookingInfo
import org.bessonov.bookinghotel.domain.repository.BookingInfoRepository
import org.bessonov.bookinghotel.domain.util.LoadingResult
import org.bessonov.bookinghotel.domain.util.NetworkProblem
import org.bessonov.bookinghotel.domain.util.SomethingWentWrong
import java.io.IOException

class BookingInfoRepositoryImpl(
    private val apiService: APIService,
    private val bookingInfoMapper: BookingInfoMapper
): BookingInfoRepository {

    override suspend fun get(): LoadingResult<BookingInfo> {
        return try {
            val response = apiService.getBookingInfo()
            bookingInfoMapper.mapResponseToLoadingResult(response = response)
        } catch (e: IOException) {
            LoadingResult.Error(message = NetworkProblem)
        } catch (e: Exception) {
            LoadingResult.Error(message = SomethingWentWrong)
        }
    }
}