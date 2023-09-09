package org.bessonov.bookinghotel.fragment.booking.adapter

import androidx.recyclerview.widget.DiffUtil

object BookingInfoItemDiffCallback : DiffUtil.ItemCallback<BookingInfoListItem>() {

    override fun areItemsTheSame(
        oldItem: BookingInfoListItem,
        newItem: BookingInfoListItem
    ): Boolean {
        return when {
            (oldItem is BookingInfoListItem.MainInfoHotelItem).and(newItem is BookingInfoListItem.MainInfoHotelItem) -> {
                (oldItem as BookingInfoListItem.MainInfoHotelItem).mainInfoHotel == (newItem as BookingInfoListItem.MainInfoHotelItem).mainInfoHotel
            }
            (oldItem is BookingInfoListItem.InfoItem).and(newItem is BookingInfoListItem.InfoItem) -> {
                (oldItem as BookingInfoListItem.InfoItem).bookingInfo.id == (newItem as BookingInfoListItem.InfoItem).bookingInfo.id
            }
            (oldItem is BookingInfoListItem.BuyerItem).and(newItem is BookingInfoListItem.BuyerItem) -> {
                true
            }
            (oldItem is BookingInfoListItem.TouristItem).and(newItem is BookingInfoListItem.TouristItem) -> {
                (oldItem as BookingInfoListItem.TouristItem).tourist.ordinalNumber == (newItem as BookingInfoListItem.TouristItem).tourist.ordinalNumber
            }
            (oldItem is BookingInfoListItem.AddTouristItem).and(newItem is BookingInfoListItem.AddTouristItem) -> {
                true
            }
            (oldItem is BookingInfoListItem.BookingPriceItem).and(newItem is BookingInfoListItem.BookingPriceItem) -> {
                (oldItem as BookingInfoListItem.BookingPriceItem).bookingPrice == (newItem as BookingInfoListItem.BookingPriceItem).bookingPrice
            }
            else -> false
        }
    }

    override fun areContentsTheSame(
        oldItem: BookingInfoListItem,
        newItem: BookingInfoListItem
    ): Boolean {
        return when {
            (oldItem is BookingInfoListItem.MainInfoHotelItem).and(newItem is BookingInfoListItem.MainInfoHotelItem) -> {
                (oldItem as BookingInfoListItem.MainInfoHotelItem) == (newItem as BookingInfoListItem.MainInfoHotelItem)
            }
            (oldItem is BookingInfoListItem.InfoItem).and(newItem is BookingInfoListItem.InfoItem) -> {
                (oldItem as BookingInfoListItem.InfoItem) == (newItem as BookingInfoListItem.InfoItem)
            }
            (oldItem is BookingInfoListItem.BuyerItem).and(newItem is BookingInfoListItem.BuyerItem) -> {
                (oldItem is BookingInfoListItem.BuyerItem) == (newItem is BookingInfoListItem.BuyerItem)
            }
            (oldItem is BookingInfoListItem.TouristItem).and(newItem is BookingInfoListItem.TouristItem) -> {
                (oldItem as BookingInfoListItem.TouristItem).tourist.isShowDetails == (newItem as BookingInfoListItem.TouristItem).tourist.isShowDetails
            }
            (oldItem is BookingInfoListItem.AddTouristItem).and(newItem is BookingInfoListItem.AddTouristItem) -> {
                true
            }
            (oldItem is BookingInfoListItem.BookingPriceItem).and(newItem is BookingInfoListItem.BookingPriceItem) -> {
                (oldItem as BookingInfoListItem.BookingPriceItem) == (newItem as BookingInfoListItem.BookingPriceItem)
            }
            else -> false
        }
    }
}