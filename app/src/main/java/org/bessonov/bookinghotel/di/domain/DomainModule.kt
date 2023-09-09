package org.bessonov.bookinghotel.di.domain

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import org.bessonov.bookinghotel.domain.repository.BookingInfoRepository
import org.bessonov.bookinghotel.domain.repository.HotelRepository
import org.bessonov.bookinghotel.domain.repository.NumberHotelRepository
import org.bessonov.bookinghotel.domain.usecase.GetBookingInfoUseCase
import org.bessonov.bookinghotel.domain.usecase.GetHotelUseCase
import org.bessonov.bookinghotel.domain.usecase.GetNumberHotelListUseCase

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetHotelUseCase(
        repository: HotelRepository
    ): GetHotelUseCase {
        return GetHotelUseCase(repository = repository)
    }

    @Provides
    fun provideGetNumberHotelListUseCase(
        repository: NumberHotelRepository
    ): GetNumberHotelListUseCase {
        return GetNumberHotelListUseCase(repository = repository)
    }

    @Provides
    fun provideGetBookingInfoUseCase(
        repository: BookingInfoRepository
    ): GetBookingInfoUseCase {
        return GetBookingInfoUseCase(repository = repository)
    }
}