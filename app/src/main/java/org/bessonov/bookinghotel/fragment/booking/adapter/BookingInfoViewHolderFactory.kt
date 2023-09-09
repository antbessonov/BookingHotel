package org.bessonov.bookinghotel.fragment.booking.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.bookinghotel.fragment.booking.adapter.BookingInfoListAdapter.Companion.VIEW_TYPE_ADD_TOURIST
import org.bessonov.bookinghotel.fragment.booking.adapter.BookingInfoListAdapter.Companion.VIEW_TYPE_BOOKING_INFO
import org.bessonov.bookinghotel.fragment.booking.adapter.BookingInfoListAdapter.Companion.VIEW_TYPE_BOOKING_PRICE
import org.bessonov.bookinghotel.fragment.booking.adapter.BookingInfoListAdapter.Companion.VIEW_TYPE_BUYER_INFO
import org.bessonov.bookinghotel.fragment.booking.adapter.BookingInfoListAdapter.Companion.VIEW_TYPE_MAIN_INFO_HOTEL
import org.bessonov.bookinghotel.fragment.booking.adapter.BookingInfoListAdapter.Companion.VIEW_TYPE_TOURIST
import org.bessonov.bookinghotel.fragment.booking.adapter.viewholder.*
import org.bessonov.bookinghotel.util.BuyerField
import org.bessonov.bookinghotel.util.TouristField
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookingInfoViewHolderFactory @Inject constructor() {

    fun create(
        parent: ViewGroup,
        viewType: Int,
        onBuyerChanged: ((CharSequence?, BuyerField) -> Unit)?,
        onBuyerFocusChange: ((Boolean, BuyerField) -> Unit)?,
        onShowTouristDetailsClick: ((Int) -> Unit)?,
        onTouristChanged: ((CharSequence?, Int, TouristField) -> Unit)?,
        onTouristFocusChange: ((Boolean, Int, TouristField) -> Unit)?,
        onAddTouristClick: (() -> Unit)?,
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MAIN_INFO_HOTEL -> MainInfoHotelViewHolder(parent = parent)
            VIEW_TYPE_BOOKING_INFO -> BookingInfoViewHolder(parent = parent)
            VIEW_TYPE_BUYER_INFO -> BuyerInfoViewHolder(
                parent = parent,
                onBuyerChanged = onBuyerChanged,
                onBuyerFocusChange = onBuyerFocusChange
            )
            VIEW_TYPE_TOURIST -> TouristViewHolder(
                parent = parent,
                onShowTouristDetailsClick = onShowTouristDetailsClick,
                onTouristChanged = onTouristChanged,
                onTouristFocusChange = onTouristFocusChange
            )
            VIEW_TYPE_ADD_TOURIST -> AddTouristViewHolder(
                parent = parent,
                onAddTouristClick = onAddTouristClick
            )
            VIEW_TYPE_BOOKING_PRICE -> BookingPriceViewHolder(parent = parent)
            else -> {
                throw RuntimeException("Unknown view type: $viewType")
            }
        }
    }
}