package org.bessonov.bookinghotel.domain.usecase

import org.bessonov.bookinghotel.domain.model.NumberHotel
import org.bessonov.bookinghotel.domain.repository.NumberHotelRepository
import org.bessonov.bookinghotel.domain.util.LoadingResult

class GetNumberHotelListUseCase(private val repository: NumberHotelRepository) {

    suspend operator fun invoke(): LoadingResult<List<NumberHotel>> {
        return repository.getList()
    }
}