package org.bessonov.bookinghotel.model.converter

import org.bessonov.bookinghotel.domain.model.Rating
import org.bessonov.bookinghotel.model.RatingUi
import javax.inject.Inject

class RatingUiConverter @Inject constructor() {

    fun mapEntityToUiModel(entity: Rating): RatingUi {
        return RatingUi(
            value = entity.value.toString(),
            description = entity.description
        )
    }
}
