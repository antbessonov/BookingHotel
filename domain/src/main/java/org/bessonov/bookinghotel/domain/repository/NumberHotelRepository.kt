package org.bessonov.bookinghotel.domain.repository

import org.bessonov.bookinghotel.domain.model.NumberHotel
import org.bessonov.bookinghotel.domain.util.LoadingResult

interface NumberHotelRepository {

    suspend fun getList(): LoadingResult<List<NumberHotel>>
}