package org.bessonov.bookinghotel.fragment.number

import org.bessonov.bookinghotel.model.NumberHotelUi


sealed class NumberHotelState {

    object Loading : NumberHotelState()

    data class Content(
        val numberHotelList: List<NumberHotelUi>
    ) : NumberHotelState()

    object Error : NumberHotelState()
}
