package org.bessonov.bookinghotel.fragment.hotel

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
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
import org.bessonov.bookinghotel.databinding.FragmentHotelBinding
import org.bessonov.bookinghotel.domain.util.NetworkProblem
import org.bessonov.bookinghotel.domain.util.SomethingWentWrong
import org.bessonov.bookinghotel.util.adapter.image.ImageListAdapter
import org.bessonov.bookinghotel.util.adapter.peculiarity.PeculiarityListAdapter
import org.bessonov.bookinghotel.fragment.number.NumberHotelFragment.Companion.NAME_HOTEL
import org.bessonov.bookinghotel.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HotelFragment : Fragment(R.layout.fragment_hotel) {

    private val binding: FragmentHotelBinding by viewBinding()

    private val viewModel by lazy {
        ViewModelProvider(this)[HotelViewModel::class.java]
    }

    @Inject
    lateinit var imageListAdapter: ImageListAdapter

    @Inject
    lateinit var pageTransformer: ZoomOutPageTransformer

    @Inject
    lateinit var peculiarityListAdapter: PeculiarityListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModelState()
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

    private fun handleState(state: HotelState) {
        when (state) {
            is HotelState.Loading -> reduce(state = state)
            is HotelState.Content -> reduce(state = state)
            is HotelState.Error -> reduce(state = state)
        }
    }

    private fun reduce(state: HotelState.Loading) {
        showLoadingProgress()
    }

    private fun showLoadingProgress() {
        binding.loadingProgress.visibility = View.VISIBLE
    }

    private fun hideLoadingProgress() {
        binding.loadingProgress.visibility = View.GONE
    }

    private fun reduce(state: HotelState.Content) {
        hideLoadingProgress()
        hideError()
        showContentLayout()
        setMainInfo(state = state)
        setAdditionalInfo(state = state)
        setNumberSelectionClickListener(state = state)
    }

    private fun showContentLayout() {
        binding.mainInfoCard.fadeIn()
        binding.additionalInfoCard.fadeIn()
        binding.buttonContainer.fadeIn()
        binding.numberSelectionBtn.fadeIn()
    }

    private fun setMainInfo(state: HotelState.Content) {
        setImageHotelList(state = state)
        binding.imageListVp.setDots(imageDotsLayout = binding.imageDots)
        binding.mainInfoHotel.setRating(
            rating = getString(
                R.string.rating_value_with_description,
                state.hotel.rating.value,
                state.hotel.rating.description
            )
        )
        binding.mainInfoHotel.setName(state.hotel.mainInfo.name)
        binding.mainInfoHotel.setAddress(state.hotel.mainInfo.address)
        binding.minPriceTv.text = getString(
            R.string.min_price_with_symbol,
            state.hotel.minPrice.value
        )
        binding.descriptionPriceTv.text = state.hotel.minPrice.description
    }

    private fun setImageHotelList(state: HotelState.Content) {
        binding.imageListVp.adapter = imageListAdapter
        imageListAdapter.submitList(state.hotel.imageList)
        binding.imageListVp.setPageTransformer(pageTransformer)
        binding.imageListVp.removeOverScroll()
    }

    private fun setAdditionalInfo(state: HotelState.Content) {
        setPeculiarityList(state = state)
        binding.descriptionTv.text = state.hotel.additionalInfo.description
        binding.peculiarityListRv.adapter = peculiarityListAdapter
        peculiarityListAdapter.submitList(state.hotel.additionalInfo.peculiarityList)
    }

    private fun setPeculiarityList(state: HotelState.Content) {
        binding.peculiarityListRv.adapter = peculiarityListAdapter
        peculiarityListAdapter.submitList(state.hotel.additionalInfo.peculiarityList)
    }

    private fun setNumberSelectionClickListener(state: HotelState.Content) {
        binding.numberSelectionBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_hotel_to_navigation_number_hotel,
                bundleOf(NAME_HOTEL to state.hotel.mainInfo.name)
            )
        }
    }

    private fun reduce(state: HotelState.Error) {
        hideLoadingProgress()
        showErrorContentLayout()
        setErrorContent(state = state)
        binding.updateBtn.setOnClickListener {
            viewModel.getHotel()
        }
    }

    private fun showErrorContentLayout() {
        binding.errorTitleTv.fadeIn()
        binding.errorDescriptionTv.fadeIn()
        binding.updateBtn.fadeIn()
    }

    private fun setErrorContent(state: HotelState.Error) {
        when (state.message) {
            NetworkProblem -> {
                binding.errorTitleTv.text = getString(R.string.network_problem)
                binding.errorDescriptionTv.text = getString(
                    R.string.error_network_problem_description
                )
            }
            SomethingWentWrong -> {
                binding.errorTitleTv.text = getString(R.string.something_went_wrong)
                binding.errorDescriptionTv.text = getString(
                    R.string.error_something_went_wrong_description
                )
            }
        }
    }

    private fun hideError() {
        binding.errorTitleTv.visibility = View.GONE
        binding.errorDescriptionTv.visibility = View.GONE
        binding.updateBtn.visibility = View.GONE
    }
}