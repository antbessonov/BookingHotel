package org.bessonov.bookinghotel.fragment.booking.adapter

import androidx.annotation.ColorRes
import org.bessonov.bookinghotel.domain.model.MainInfoHotel
import org.bessonov.bookinghotel.model.TouristUi
import org.bessonov.bookinghotel.model.BookingInfoUi
import org.bessonov.bookinghotel.model.BookingPriceUi
import org.bessonov.bookinghotel.model.RatingUi

sealed class BookingInfoListItem {

    data class MainInfoHotelItem(
        val rating: RatingUi,
        val mainInfoHotel: MainInfoHotel
    ) : BookingInfoListItem()

    data class InfoItem(
        val bookingInfo: BookingInfoUi
    ) : BookingInfoListItem()

    data class BuyerItem(
        val phone: String,
        @ColorRes val phoneColorIntField: Int,
        val email: String,
        @ColorRes val emailColorIntField: Int,
    ) : BookingInfoListItem()

    data class TouristItem(
        val tourist: TouristUi
    ) : BookingInfoListItem()

    object AddTouristItem : BookingInfoListItem()

    data class BookingPriceItem(
        val bookingPrice: BookingPriceUi
    ) : BookingInfoListItem()
}
