package org.bessonov.bookinghotel.fragment.paid

sealed class SuccessPaidState {

    object Loading : SuccessPaidState()

    data class Content(
        val numberOrder: String
    ) : SuccessPaidState()

    object Error : SuccessPaidState()
}
