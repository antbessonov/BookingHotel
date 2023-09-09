package org.bessonov.bookinghotel.fragment.number

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.bessonov.bookinghotel.domain.model.NumberHotel
import org.bessonov.bookinghotel.domain.usecase.GetNumberHotelListUseCase
import org.bessonov.bookinghotel.domain.util.LoadingResult
import org.bessonov.bookinghotel.model.converter.NumberHotelUiConverter
import javax.inject.Inject

@HiltViewModel
class NumberHotelViewModel @Inject constructor(
    private val getNumberHotelListUseCase: GetNumberHotelListUseCase,
    private val numberHotelUiConverter: NumberHotelUiConverter
) : ViewModel() {

    private val _uiState: MutableStateFlow<NumberHotelState> = MutableStateFlow(
        NumberHotelState.Loading
    )
    val uiState: StateFlow<NumberHotelState> = _uiState.asStateFlow()

    init {
        getNumberHotel()
    }

    private fun getNumberHotel() {
        viewModelScope.launch {
            val loadingResult = getNumberHotelListUseCase()
            handleLoadingResult(loadingResult = loadingResult)
        }
    }

    private fun handleLoadingResult(loadingResult: LoadingResult<List<NumberHotel>>) {
        when (loadingResult) {
            is LoadingResult.Error -> reduce(loadingResult = loadingResult)
            is LoadingResult.Success -> reduce(loadingResult = loadingResult)
            LoadingResult.Loading -> Unit
        }
    }

    private fun reduce(loadingResult: LoadingResult.Error) {
        _uiState.update { NumberHotelState.Error }
    }

    private fun reduce(loadingResult: LoadingResult.Success<List<NumberHotel>>) {
        _uiState.update {
            NumberHotelState.Content(
                numberHotelList = numberHotelUiConverter.mapEntityListToUiModelList(
                    entityList = loadingResult.value
                )
            )
        }
    }
}