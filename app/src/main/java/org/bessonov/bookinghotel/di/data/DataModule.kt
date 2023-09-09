package org.bessonov.bookinghotel.di.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.bessonov.bookinghotel.data.mapper.BookingInfoMapper
import org.bessonov.bookinghotel.data.mapper.HotelMapper
import org.bessonov.bookinghotel.data.mapper.NumberHotelMapper
import org.bessonov.bookinghotel.data.network.APIService
import org.bessonov.bookinghotel.data.repository.BookingInfoRepositoryImpl
import org.bessonov.bookinghotel.data.repository.HotelRepositoryImpl
import org.bessonov.bookinghotel.data.repository.NumberHotelRepositoryImpl
import org.bessonov.bookinghotel.domain.repository.BookingInfoRepository
import org.bessonov.bookinghotel.domain.repository.HotelRepository
import org.bessonov.bookinghotel.domain.repository.NumberHotelRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideHotelRepository(
        apiService: APIService,
        hotelMapper: HotelMapper
    ): HotelRepository {
        return HotelRepositoryImpl(apiService = apiService, hotelMapper = hotelMapper)
    }

    @Provides
    @Singleton
    fun provideHotelMapper(): HotelMapper {
        return HotelMapper()
    }

    @Provides
    @Singleton
    fun provideNumberHotelRepository(
        apiService: APIService,
        numberHotelMapper: NumberHotelMapper
    ): NumberHotelRepository {
        return NumberHotelRepositoryImpl(
            apiService = apiService,
            numberHotelMapper = numberHotelMapper
        )
    }

    @Provides
    @Singleton
    fun provideNumberHotelMapper(): NumberHotelMapper {
        return NumberHotelMapper()
    }

    @Provides
    @Singleton
    fun provideBookingInfoRepository(
        apiService: APIService,
        bookingInfoMapper: BookingInfoMapper
    ): BookingInfoRepository {
        return BookingInfoRepositoryImpl(
            apiService = apiService,
            bookingInfoMapper = bookingInfoMapper
        )
    }

    @Provides
    @Singleton
    fun provideBookingInfoMapper(): BookingInfoMapper {
        return BookingInfoMapper()
    }
}