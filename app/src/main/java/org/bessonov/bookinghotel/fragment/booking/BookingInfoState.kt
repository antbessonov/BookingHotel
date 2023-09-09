package org.bessonov.bookinghotel.fragment.booking

import androidx.annotation.ColorRes
import org.bessonov.bookinghotel.model.TouristUi
import org.bessonov.bookinghotel.domain.util.LoadingError
import org.bessonov.bookinghotel.model.BookingInfoUi

sealed class BookingInfoState {

    object Loading : BookingInfoState()

    data class Content(
        val bookingInfo: BookingInfoUi,
        val phone: String,
        val isPhoneValid: Boolean,
        @ColorRes val phoneColorIntField: Int,
        val email: String,
        val isEmailValid: Boolean,
        @ColorRes val emailColorIntField: Int,
        val touristList: List<TouristUi>
    ) : BookingInfoState()

    data class Error(
        val message: LoadingError
    ) : BookingInfoState()
}
