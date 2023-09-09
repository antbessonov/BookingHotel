package org.bessonov.bookinghotel.model.converter

import org.bessonov.bookinghotel.domain.model.Price
import org.bessonov.bookinghotel.model.PriceUi
import java.text.NumberFormat
import javax.inject.Inject

class PriceUiConverter @Inject constructor() {

    fun mapEntityToUiModel(entity: Price): PriceUi {
        return PriceUi(
            value = NumberFormat.getInstance().format(entity.value),
            description = entity.description.replaceFirstChar(Char::lowercaseChar)
        )
    }
}