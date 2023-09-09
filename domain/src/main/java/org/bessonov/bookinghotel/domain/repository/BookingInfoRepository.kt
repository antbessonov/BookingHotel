package org.bessonov.bookinghotel.domain.repository

import org.bessonov.bookinghotel.domain.model.BookingInfo
import org.bessonov.bookinghotel.domain.util.LoadingResult

interface BookingInfoRepository {

    suspend fun get(): LoadingResult<BookingInfo>
}