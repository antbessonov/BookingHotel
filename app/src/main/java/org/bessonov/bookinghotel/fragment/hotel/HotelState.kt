package org.bessonov.bookinghotel.fragment.hotel

import org.bessonov.bookinghotel.domain.util.LoadingError
import org.bessonov.bookinghotel.model.HotelUi

sealed class HotelState {

    object Loading : HotelState()

    data class Content(
        val hotel: HotelUi
    ) : HotelState()

    data class Error(
        val message: LoadingError
    ) : HotelState()
}
