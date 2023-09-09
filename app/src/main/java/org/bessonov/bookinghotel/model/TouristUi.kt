package org.bessonov.bookinghotel.model

import androidx.annotation.ColorRes

data class TouristUi(
    val ordinalNumber: Int,
    val isShowDetails: Boolean,
    val firstName: String,
    @ColorRes val firstNameColorIntField: Int,
    val lastName: String,
    @ColorRes val lastNameColorIntField: Int,
    val birthdate: String,
    @ColorRes val birthdateColorIntField: Int,
    val citizenship: String,
    @ColorRes val citizenshipColorIntField: Int,
    val numberInterPassport: String,
    @ColorRes val numberInterPassportColorIntField: Int,
    val validityPeriodInterPassport: String,
    @ColorRes val validityPeriodInterPassportColorIntField: Int,
) {

    companion object {

        const val MAX_ORDINAL_NUMBER = 10
    }
}
