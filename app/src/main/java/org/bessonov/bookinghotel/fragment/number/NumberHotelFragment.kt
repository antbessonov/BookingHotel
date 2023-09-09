package org.bessonov.bookinghotel.fragment.number

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
import org.bessonov.bookinghotel.databinding.FragmentNumberHotelBinding
import org.bessonov.bookinghotel.fragment.number.adapter.NumberHotelUiListAdapter
import org.bessonov.bookinghotel.util.fadeIn
import javax.inject.Inject
import kotlin.properties.Delegates

@AndroidEntryPoint
class NumberHotelFragment : Fragment(R.layout.fragment_number_hotel) {

    private val binding: FragmentNumberHotelBinding by viewBinding()

    private val viewModel by lazy {
        ViewModelProvider(this)[NumberHotelViewModel::class.java]
    }

    private var nameHotel by Delegates.notNull<String>()

    @Inject
    lateinit var numberHotelUiListAdapter: NumberHotelUiListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationBackClickListener()
        customNumberHotelListRv()
        observeViewModelState()
    }

    private fun parseParams() {
        val name = requireArguments().getString(NAME_HOTEL)
        nameHotel = name ?: throw RuntimeException("Parameter \"name hotel\" is absent.")
    }

    private fun setNavigationBackClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun customNumberHotelListRv() {
        binding.numberHotelListRv.adapter = numberHotelUiListAdapter
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

    private fun handleState(state: NumberHotelState) {
        when (state) {
            is NumberHotelState.Loading -> reduce(state = state)
            is NumberHotelState.Content -> reduce(state = state)
            is NumberHotelState.Error -> reduce(state = state)
        }
    }

    private fun reduce(state: NumberHotelState.Loading) {
        showLoadingProgress()
    }

    private fun showLoadingProgress() {
        binding.loadingProgress.visibility = View.VISIBLE
    }

    private fun hideLoadingProgress() {
        binding.loadingProgress.visibility = View.GONE
    }

    private fun reduce(state: NumberHotelState.Content) {
        hideLoadingProgress()
        showContentLayout()
        setNameHotel()
        setNumberHotelList(state = state)
        setNumberHotelClickListener()
    }

    private fun showContentLayout() {
        binding.numberHotelListRv.fadeIn()
    }

    private fun setNameHotel() {
        binding.toolbar.title = nameHotel
    }

    private fun setNumberHotelList(state: NumberHotelState.Content) {
        numberHotelUiListAdapter.submitList(state.numberHotelList)
    }

    private fun setNumberHotelClickListener() {
        numberHotelUiListAdapter.onItemClick = {
            findNavController().navigate(
                R.id.action_navigation_number_hotel_to_navigation_booking_number_hotel
            )
        }
    }

    private fun reduce(state: NumberHotelState.Error) {
        hideLoadingProgress()
    }

    companion object {

        const val NAME_HOTEL = "name_hotel"
    }
}