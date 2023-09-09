package org.bessonov.bookinghotel.fragment.paid

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SuccessPaidViewModel @Inject constructor() : ViewModel() {

    val uiState: StateFlow<SuccessPaidState> = flow {
        emit((MIN_VALUE..MAX_VALUE).random())
    }
        .map { numberOrder ->
            SuccessPaidState.Content(numberOrder = numberOrder.toString())
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = SuccessPaidState.Loading
        )

    companion object {

        private const val MIN_VALUE = 1000
        private const val MAX_VALUE = 10000
    }
}