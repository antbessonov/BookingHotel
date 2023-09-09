package org.bessonov.bookinghotel.domain.usecase

import org.bessonov.bookinghotel.domain.model.BookingInfo
import org.bessonov.bookinghotel.domain.repository.BookingInfoRepository
import org.bessonov.bookinghotel.domain.util.LoadingResult

class GetBookingInfoUseCase(private val repository: BookingInfoRepository) {

    suspend operator fun invoke(): LoadingResult<BookingInfo> {
        return repository.get()
    }
}