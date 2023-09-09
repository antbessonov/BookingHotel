package org.bessonov.bookinghotel.domain.util

sealed class LoadingResult<out T> {

    object Loading: LoadingResult<Nothing>()
    data class Success<out T>(val value: T) : LoadingResult<T>()
    data class Error(val message: LoadingError) : LoadingResult<Nothing>()
}
