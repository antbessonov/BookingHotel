package org.bessonov.bookinghotel.domain.usecase

import org.bessonov.bookinghotel.domain.model.Hotel
import org.bessonov.bookinghotel.domain.repository.HotelRepository
import org.bessonov.bookinghotel.domain.util.LoadingResult

class GetHotelUseCase(private val repository: HotelRepository) {

    suspend operator fun invoke(): LoadingResult<Hotel> {
        return repository.get()
    }
}