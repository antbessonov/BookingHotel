package org.bessonov.bookinghotel.fragment.booking.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.bookinghotel.fragment.booking.adapter.viewholder.*
import org.bessonov.bookinghotel.util.BuyerField
import org.bessonov.bookinghotel.util.TouristField
import javax.inject.Inject

class BookingInfoListAdapter @Inject constructor(
    private val bookingInfoViewHolderFactory: BookingInfoViewHolderFactory
) : ListAdapter<BookingInfoListItem, RecyclerView.ViewHolder>(BookingInfoItemDiffCallback) {

    var onBuyerChanged: ((CharSequence?, BuyerField) -> Unit)? = null
    var onBuyerFocusChange: ((Boolean, BuyerField) -> Unit)? = null
    var onShowTouristDetailsClick: ((Int) -> Unit)? = null
    var onTouristChanged: ((CharSequence?, Int, TouristField) -> Unit)? = null
    var onTouristFocusChange: ((Boolean, Int, TouristField) -> Unit)? = null
    var onAddTouristClick: (() -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return bookingInfoViewHolderFactory.create(
            parent = parent,
            viewType = viewType,
            onBuyerChanged = onBuyerChanged,
            onBuyerFocusChange = onBuyerFocusChange,
            onShowTouristDetailsClick = onShowTouristDetailsClick,
            onTouristChanged = onTouristChanged,
            onTouristFocusChange = onTouristFocusChange,
            onAddTouristClick = onAddTouristClick,
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = getItem(position)) {
            is BookingInfoListItem.MainInfoHotelItem -> (holder as MainInfoHotelViewHolder)
                .onBind(
                    rating = item.rating,
                    mainInfoHotel = item.mainInfoHotel
                )
            is BookingInfoListItem.InfoItem -> (holder as BookingInfoViewHolder)
                .onBind(bookingInfo = item.bookingInfo)
            is BookingInfoListItem.BuyerItem -> (holder as BuyerInfoViewHolder)
                .onBind(
                    phone = item.phone,
                    phoneColorIntField = item.phoneColorIntField,
                    email = item.email,
                    emailColorIntField = item.emailColorIntField
                )
            is BookingInfoListItem.TouristItem -> (holder as TouristViewHolder)
                .onBind(tourist = item.tourist)
            is BookingInfoListItem.AddTouristItem -> Unit
            is BookingInfoListItem.BookingPriceItem -> (holder as BookingPriceViewHolder)
                .onBind(
                    bookingPrice = item.bookingPrice
                )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is BookingInfoListItem.MainInfoHotelItem -> VIEW_TYPE_MAIN_INFO_HOTEL
            is BookingInfoListItem.InfoItem -> VIEW_TYPE_BOOKING_INFO
            is BookingInfoListItem.BuyerItem -> VIEW_TYPE_BUYER_INFO
            is BookingInfoListItem.TouristItem -> VIEW_TYPE_TOURIST
            is BookingInfoListItem.AddTouristItem -> VIEW_TYPE_ADD_TOURIST
            is BookingInfoListItem.BookingPriceItem -> VIEW_TYPE_BOOKING_PRICE
        }
    }

    companion object {

        const val VIEW_TYPE_MAIN_INFO_HOTEL = 1
        const val VIEW_TYPE_BOOKING_INFO = 2
        const val VIEW_TYPE_BUYER_INFO = 3
        const val VIEW_TYPE_TOURIST = 4
        const val VIEW_TYPE_ADD_TOURIST = 5
        const val VIEW_TYPE_BOOKING_PRICE = 6
    }
}