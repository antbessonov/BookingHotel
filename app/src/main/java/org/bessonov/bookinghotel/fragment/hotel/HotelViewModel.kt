package org.bessonov.bookinghotel.fragment.hotel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.bessonov.bookinghotel.domain.model.Hotel
import org.bessonov.bookinghotel.domain.usecase.GetHotelUseCase
import org.bessonov.bookinghotel.domain.util.LoadingResult
import org.bessonov.bookinghotel.model.converter.HotelUiConverter
import javax.inject.Inject

@HiltViewModel
class HotelViewModel @Inject constructor(
    private val getHotelUseCase: GetHotelUseCase,
    private val hotelUiConverter: HotelUiConverter,
) : ViewModel() {

    private val _uiState: MutableStateFlow<HotelState> = MutableStateFlow(HotelState.Loading)
    val uiState: StateFlow<HotelState> = _uiState.asStateFlow()

    init {
        getHotel()
    }

    private fun getHotel() {
        viewModelScope.launch {
            val loadingResult = getHotelUseCase()
            handleLoadingResult(loadingResult = loadingResult)
        }
    }

    private fun handleLoadingResult(loadingResult: LoadingResult<Hotel>) {
        when (loadingResult) {
            LoadingResult.Loading -> Unit
            is LoadingResult.Error -> reduce(loadingResult = loadingResult)
            is LoadingResult.Success -> reduce(loadingResult = loadingResult)
        }
    }

    private fun reduce(loadingResult: LoadingResult.Error) {
        _uiState.update { HotelState.Error(message = loadingResult.message) }
    }

    private fun reduce(loadingResult: LoadingResult.Success<Hotel>) {
        _uiState.update {
            HotelState.Content(
                hotel = hotelUiConverter.mapEntityToUiModel(entity = loadingResult.value)
            )
        }
    }
}