package org.bessonov.bookinghotel.domain.util

sealed interface LoadingError

object NetworkProblem : LoadingError
object SomethingWentWrong : LoadingError