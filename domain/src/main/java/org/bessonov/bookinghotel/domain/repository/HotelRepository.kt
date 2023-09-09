package org.bessonov.bookinghotel.domain.repository

import org.bessonov.bookinghotel.domain.model.Hotel
import org.bessonov.bookinghotel.domain.util.LoadingResult

interface HotelRepository {

    suspend fun get(): LoadingResult<Hotel>
}