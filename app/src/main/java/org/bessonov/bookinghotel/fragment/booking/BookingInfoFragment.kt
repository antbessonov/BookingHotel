package org.bessonov.bookinghotel.fragment.booking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.FragmentBookingNumberHotelBinding
import org.bessonov.bookinghotel.fragment.booking.BookingInfoViewModel.Companion.BUYER_POSITION
import org.bessonov.bookinghotel.fragment.booking.BookingInfoViewModel.Companion.FIRST_INDEX
import org.bessonov.bookinghotel.fragment.booking.BookingInfoViewModel.Companion.FIRST_TOURIST_POSITION
import org.bessonov.bookinghotel.fragment.booking.adapter.BookingInfoListAdapter
import org.bessonov.bookinghotel.fragment.booking.adapter.viewholder.BuyerInfoViewHolder
import org.bessonov.bookinghotel.fragment.booking.adapter.viewholder.TouristViewHolder
import org.bessonov.bookinghotel.util.*
import javax.inject.Inject

@AndroidEntryPoint
class BookingInfoFragment : Fragment(R.layout.fragment_booking_number_hotel) {

    private val binding: FragmentBookingNumberHotelBinding by viewBinding()

    private val viewModel by lazy {
        ViewModelProvider(this)[BookingInfoViewModel::class.java]
    }

    @Inject
    lateinit var bookingInfoListAdapter: BookingInfoListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationBackClickListener()
        customBookingInfoList()
        setOnBuyerChanged()
        setOnBuyerFocusChange()
        setTouristShowDetailsClickListener()
        setOnTouristChanged()
        setOnTouristFocusChange()
        setAddTouristClickListener()
        observeViewModelState()
    }

    private fun setNavigationBackClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun customBookingInfoList() {
        binding.bookingInfoListRv.adapter = bookingInfoListAdapter
        binding.bookingInfoListRv.itemAnimator = null
    }

    private fun setOnBuyerChanged() {
        bookingInfoListAdapter.onBuyerChanged = { text, buyerField ->
            when (buyerField) {
                BuyerField.PHONE -> {
                    viewModel.updatePhoneColorField(colorField = ColorField.DEFAULT)
                    viewModel.updatePhone(value = text)
                }
                BuyerField.EMAIL -> {
                    viewModel.updateEmailColorField(colorField = ColorField.DEFAULT)
                    viewModel.updateEmail(value = text)
                }
            }
        }
    }

    private fun setOnBuyerFocusChange() {
        bookingInfoListAdapter.onBuyerFocusChange = { hasFocus, buyerField ->
            when (buyerField) {
                BuyerField.PHONE -> {
                    if (!hasFocus) {
                        viewModel.updatePhoneColorField()
                    }
                }
                BuyerField.EMAIL -> {
                    if (!hasFocus) {
                        viewModel.updateEmailColorField()
                    }
                }
            }
        }
    }

    private fun setTouristShowDetailsClickListener() {
        bookingInfoListAdapter.onShowTouristDetailsClick = { position ->
            viewModel.controlDisplayTouristDetails(position = position)
        }
    }

    private fun setOnTouristChanged() {
        bookingInfoListAdapter.onTouristChanged = { text, position, buyerField ->
            when (buyerField) {
                TouristField.FIRST_NAME -> {
                    viewModel.updateFirstNameFirstTouristColorField(
                        colorField = ColorField.DEFAULT
                    )
                    viewModel.updateFirstNameTouristList(
                        value = text,
                        position = position
                    )
                }
                TouristField.LAST_NAME -> {
                    viewModel.updateLastNameFirstTouristColorField(
                        colorField = ColorField.DEFAULT
                    )
                    viewModel.updateLastNameTouristList(
                        value = text,
                        position = position
                    )
                }
                TouristField.BIRTHDATE -> {
                    viewModel.updateBirthdateFirstTouristColorField(
                        colorField = ColorField.DEFAULT
                    )
                    viewModel.updateBirthdateTouristList(
                        value = text,
                        position = position
                    )
                }
                TouristField.CITIZENSHIP -> {
                    viewModel.updateCitizenshipFirstTouristColorField(
                        colorField = ColorField.DEFAULT
                    )
                    viewModel.updateCitizenshipTouristList(
                        value = text,
                        position = position
                    )
                }
                TouristField.NUMBER_INTER_PASSPORT -> {
                    viewModel.updateNumberInterPassportTouristColorField(
                        colorField = ColorField.DEFAULT
                    )
                    viewModel.updateNumberInterPassportTouristList(
                        value = text,
                        position = position
                    )
                }
                TouristField.VALIDITY_PERIOD_PASSPORT -> {
                    viewModel.updateValidityPeriodInterPassportFirstTouristColorField(
                        colorField = ColorField.DEFAULT
                    )
                    viewModel.updateValidityPeriodInterPassportTouristList(
                        value = text,
                        position = position
                    )
                }
            }
        }
    }

    private fun setOnTouristFocusChange() {
        bookingInfoListAdapter.onTouristFocusChange = { hasFocus, _, buyerField ->
            when (buyerField) {
                TouristField.FIRST_NAME -> {
                    if (!hasFocus) {
                        viewModel.updateFirstNameFirstTouristColorField()
                    }
                }
                TouristField.LAST_NAME -> {
                    if (!hasFocus) {
                        viewModel.updateLastNameFirstTouristColorField()
                    }
                }
                TouristField.BIRTHDATE -> {
                    if (!hasFocus) {
                        viewModel.updateBirthdateFirstTouristColorField()
                    }
                }
                TouristField.CITIZENSHIP -> {
                    if (!hasFocus) {
                        viewModel.updateCitizenshipFirstTouristColorField()
                    }
                }
                TouristField.NUMBER_INTER_PASSPORT -> {
                    if (!hasFocus) {
                        viewModel.updateNumberInterPassportTouristColorField()
                    }
                }
                TouristField.VALIDITY_PERIOD_PASSPORT -> {
                    if (!hasFocus) {
                        viewModel.updateValidityPeriodInterPassportFirstTouristColorField()
                    }
                }
            }
        }
    }

    private fun setAddTouristClickListener() {
        bookingInfoListAdapter.onAddTouristClick = {
            viewModel.updateTouristList()
        }
    }

    private fun observeViewModelState() {
        viewModel.uiState
            .flowWithLifecycle(
                lifecycle = viewLifecycleOwner.lifecycle,
                minActiveState = Lifecycle.State.CREATED
            )
            .onEach { state -> handleState(state = state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: BookingInfoState) {
        when (state) {
            is BookingInfoState.Loading -> reduce(state = state)
            is BookingInfoState.Content -> reduce(state = state)
            is BookingInfoState.Error -> reduce(state = state)
        }
    }

    private fun reduce(state: BookingInfoState.Loading) {
        showLoadingProgress()
    }

    private fun showLoadingProgress() {
        binding.loadingProgress.visibility = View.VISIBLE
    }

    private fun hideLoadingProgress() {
        binding.loadingProgress.visibility = View.GONE
    }

    private fun reduce(state: BookingInfoState.Content) {
        hideLoadingProgress()
        showContentLayout()
        setBookingInfoList()
        setPayBookingBtnText(state = state)
        setPayBookingClickListener(state = state)
    }

    private fun showContentLayout() {
        binding.bookingInfoListRv.fadeIn()
        binding.buttonContainer.fadeIn()
        binding.payBookingBtn.fadeIn()
    }

    private fun setBookingInfoList() {
        val bookingInfoList = viewModel.getBookingInfoList()
        bookingInfoListAdapter.submitList(bookingInfoList)
    }

    private fun setPayBookingBtnText(state: BookingInfoState.Content) {
        binding.payBookingBtn.text = getString(
            R.string.pay_booking_sample,
            state.bookingInfo.price.totalPrice
        )
    }

    private fun setPayBookingClickListener(state: BookingInfoState.Content) {
        binding.payBookingBtn.setOnClickListener {
            checkPhoneBuyerState(state = state)
            checkEmailBuyerState(state = state)
            checkFirstTouristState(state = state)
            if ((state.isPhoneValid)
                    .and(state.isEmailValid)
                    .and(state.touristList.first().firstName.isNotEmpty())
                    .and(state.touristList.first().lastName.isNotEmpty())
                    .and(state.touristList.first().birthdate.isNotEmpty())
                    .and(state.touristList.first().citizenship.isNotEmpty())
                    .and(state.touristList.first().numberInterPassport.isNotEmpty())
                    .and(state.touristList.first().validityPeriodInterPassport.isNotEmpty())
            ) {
                findNavController().navigate(
                    R.id.action_navigation_booking_number_hotel_to_navigation_success_paid
                )
            }
        }
    }

    private fun checkPhoneBuyerState(state: BookingInfoState.Content) {
        if (!state.isPhoneValid) {
            viewModel.updatePhoneColorField(colorField = ColorField.ERROR)
            val viewHolder = binding.bookingInfoListRv
                .findViewHolderForAdapterPosition(BUYER_POSITION)
            if (viewHolder == null) {
                binding.bookingInfoListRv.scrollToPosition(position = BUYER_POSITION) {
                    val newViewHolder = binding.bookingInfoListRv
                        .findViewHolderForAdapterPosition(BUYER_POSITION)
                    (newViewHolder as BuyerInfoViewHolder).setPhoneErrorColor()
                }
            } else {
                (viewHolder as BuyerInfoViewHolder).setPhoneErrorColor()
            }
        }
    }


    private fun checkEmailBuyerState(state: BookingInfoState.Content) {
        if (!state.isEmailValid) {
            viewModel.updateEmailColorField(colorField = ColorField.ERROR)
            val viewHolder = binding.bookingInfoListRv
                .findViewHolderForLayoutPosition(BUYER_POSITION)
            if (viewHolder == null) {
                binding.bookingInfoListRv.scrollToPosition(position = BUYER_POSITION) {
                    val newViewHolder = binding.bookingInfoListRv
                        .findViewHolderForAdapterPosition(BUYER_POSITION)
                    (newViewHolder as BuyerInfoViewHolder).setEmailErrorColor()
                }
            } else {
                (viewHolder as BuyerInfoViewHolder).setEmailErrorColor()
            }
        }
    }

    private fun checkFirstTouristState(state: BookingInfoState.Content) {
        checkShowTouristDetails(state = state)
        checkFirstNameFirstTouristState(state = state)
        checkLastNameFirstTouristState(state = state)
        checkBirthdateFirstTouristState(state = state)
        checkCitizenshipFirstTouristState(state = state)
        checkNumberInterPassportFirstTouristState(state = state)
        checkValidityPeriodInterPassportFirstTouristState(state = state)
    }

    private fun checkFirstNameFirstTouristState(state: BookingInfoState.Content) {
        val firstTourist = state.touristList.first()
        if (firstTourist.firstName.isEmpty()) {
            viewModel.updateFirstNameFirstTouristColorField(colorField = ColorField.ERROR)
            val viewHolder = binding.bookingInfoListRv
                .findViewHolderForLayoutPosition(FIRST_TOURIST_POSITION)
            if (viewHolder == null) {
                val position = getScrollPositionToFirstTourist(state = state)
                binding.bookingInfoListRv.scrollToPosition(position = position) {
                    val newViewHolder = binding.bookingInfoListRv
                        .findViewHolderForAdapterPosition(FIRST_TOURIST_POSITION)
                    (newViewHolder as TouristViewHolder).setFirstNameFirstTouristErrorColor()
                }
            } else {
                (viewHolder as TouristViewHolder).setFirstNameFirstTouristErrorColor()
            }
        }
    }

    private fun checkLastNameFirstTouristState(state: BookingInfoState.Content) {
        val firstTourist = state.touristList.first()
        if (firstTourist.lastName.isEmpty()) {
            viewModel.updateLastNameFirstTouristColorField(colorField = ColorField.ERROR)
            val viewHolder = binding.bookingInfoListRv
                .findViewHolderForLayoutPosition(FIRST_TOURIST_POSITION)
            if (viewHolder == null) {
                val position = getScrollPositionToFirstTourist(state = state)
                binding.bookingInfoListRv.scrollToPosition(position = position) {
                    val newViewHolder = binding.bookingInfoListRv
                        .findViewHolderForAdapterPosition(FIRST_TOURIST_POSITION)
                    (newViewHolder as TouristViewHolder).setLastNameFirstTouristErrorColor()
                }
            } else {
                (viewHolder as TouristViewHolder).setLastNameFirstTouristErrorColor()
            }
        }
    }

    private fun checkBirthdateFirstTouristState(state: BookingInfoState.Content) {
        val firstTourist = state.touristList.first()
        if (firstTourist.birthdate.isEmpty()) {
            viewModel.updateBirthdateFirstTouristColorField(colorField = ColorField.ERROR)
            val viewHolder = binding.bookingInfoListRv
                .findViewHolderForLayoutPosition(FIRST_TOURIST_POSITION)
            if (viewHolder == null) {
                val position = getScrollPositionToFirstTourist(state = state)
                binding.bookingInfoListRv.scrollToPosition(position = position) {
                    val newViewHolder = binding.bookingInfoListRv
                        .findViewHolderForAdapterPosition(FIRST_TOURIST_POSITION)
                    (newViewHolder as TouristViewHolder).setBirthdateFirstTouristErrorColor()
                }
            } else {
                (viewHolder as TouristViewHolder).setBirthdateFirstTouristErrorColor()
            }
        }
    }

    private fun checkCitizenshipFirstTouristState(state: BookingInfoState.Content) {
        val firstTourist = state.touristList.first()
        if (firstTourist.citizenship.isEmpty()) {
            viewModel.updateCitizenshipFirstTouristColorField(colorField = ColorField.ERROR)
            val viewHolder = binding.bookingInfoListRv
                .findViewHolderForLayoutPosition(FIRST_TOURIST_POSITION)
            if (viewHolder == null) {
                val position = getScrollPositionToFirstTourist(state = state)
                binding.bookingInfoListRv.scrollToPosition(position = position) {
                    val newViewHolder = binding.bookingInfoListRv
                        .findViewHolderForAdapterPosition(FIRST_TOURIST_POSITION)
                    (newViewHolder as TouristViewHolder).setCitizenshipFirstTouristErrorColor()
                }
            } else {
                (viewHolder as TouristViewHolder).setCitizenshipFirstTouristErrorColor()
            }
        }
    }

    private fun checkNumberInterPassportFirstTouristState(state: BookingInfoState.Content) {
        val firstTourist = state.touristList.first()
        if (firstTourist.numberInterPassport.isEmpty()) {
            viewModel.updateNumberInterPassportTouristColorField(colorField = ColorField.ERROR)
            val viewHolder = binding.bookingInfoListRv
                .findViewHolderForLayoutPosition(FIRST_TOURIST_POSITION)
            if (viewHolder == null) {
                val position = getScrollPositionToFirstTourist(state = state)
                binding.bookingInfoListRv.scrollToPosition(position = position) {
                    val newViewHolder = binding.bookingInfoListRv
                        .findViewHolderForAdapterPosition(FIRST_TOURIST_POSITION)
                    (newViewHolder as TouristViewHolder).setNumberInterPassportFirstTouristErrorColor()
                }
            } else {
                (viewHolder as TouristViewHolder).setNumberInterPassportFirstTouristErrorColor()
            }
        }
    }

    private fun checkValidityPeriodInterPassportFirstTouristState(state: BookingInfoState.Content) {
        val firstTourist = state.touristList.first()
        if (firstTourist.validityPeriodInterPassport.isEmpty()) {
            viewModel.updateValidityPeriodInterPassportFirstTouristColorField(colorField = ColorField.ERROR)
            val viewHolder = binding.bookingInfoListRv
                .findViewHolderForLayoutPosition(FIRST_TOURIST_POSITION)
            if (viewHolder == null) {
                val position = getScrollPositionToFirstTourist(state = state)
                binding.bookingInfoListRv.scrollToPosition(position = position) {
                    val newViewHolder = binding.bookingInfoListRv
                        .findViewHolderForAdapterPosition(FIRST_TOURIST_POSITION)
                    (newViewHolder as TouristViewHolder)
                        .setValidityPeriodInternationalPassportFirstTouristErrorColor()
                }
            } else {
                (viewHolder as TouristViewHolder)
                    .setValidityPeriodInternationalPassportFirstTouristErrorColor()
            }
        }
    }

    private fun getScrollPositionToFirstTourist(state: BookingInfoState.Content): Int {
        return if ((state.isEmailValid).and(state.isPhoneValid)) {
            FIRST_TOURIST_POSITION
        } else {
            BUYER_POSITION
        }
    }

    private fun checkShowTouristDetails(state: BookingInfoState.Content) {
        if (!state.touristList.first().isShowDetails) {
            viewModel.controlDisplayTouristDetails(position = FIRST_INDEX)
        }
    }

    private fun reduce(state: BookingInfoState.Error) {
        hideLoadingProgress()
    }
}