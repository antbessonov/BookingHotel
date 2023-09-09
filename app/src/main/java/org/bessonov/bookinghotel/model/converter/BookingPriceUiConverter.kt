package org.bessonov.bookinghotel.model.converter

import org.bessonov.bookinghotel.domain.model.BookingPrice
import org.bessonov.bookinghotel.model.BookingPriceUi
import java.text.NumberFormat
import javax.inject.Inject

class BookingPriceUiConverter @Inject constructor() {

    fun mapEntityToUiModel(entity: BookingPrice): BookingPriceUi {
        return BookingPriceUi(
            tourPrice = NumberFormat.getInstance().format(entity.tourPrice),
            fuelCharge = NumberFormat.getInstance().format(entity.fuelCharge),
            serviceCharge = NumberFormat.getInstance().format(entity.serviceCharge),
            totalPrice = getTotalPrice(
                tourPrice = entity.tourPrice,
                fuelCharge = entity.fuelCharge,
                serviceCharge = entity.serviceCharge
            )
        )
    }

    private fun getTotalPrice(tourPrice: Int, fuelCharge: Int, serviceCharge: Int): String {
        val result = tourPrice + fuelCharge + serviceCharge
        return NumberFormat.getInstance().format(result)
    }
}
