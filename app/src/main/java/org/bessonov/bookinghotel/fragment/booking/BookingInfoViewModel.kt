package org.bessonov.bookinghotel.fragment.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.bessonov.bookinghotel.domain.model.BookingInfo
import org.bessonov.bookinghotel.domain.usecase.GetBookingInfoUseCase
import org.bessonov.bookinghotel.domain.util.LoadingResult
import org.bessonov.bookinghotel.model.TouristUi
import org.bessonov.bookinghotel.model.converter.BookingInfoUiConverter
import org.bessonov.bookinghotel.util.ColorField
import org.bessonov.bookinghotel.util.isValidEmail
import org.bessonov.bookinghotel.util.isValidPhone
import javax.inject.Inject

@HiltViewModel
class BookingInfoViewModel @Inject constructor(
    private val getBookingInfoUseCase: GetBookingInfoUseCase,
    private val bookingInfoUiConverter: BookingInfoUiConverter
) : ViewModel() {

    private val _uiState: MutableStateFlow<BookingInfoState> = MutableStateFlow(
        BookingInfoState.Loading
    )
    val uiState: StateFlow<BookingInfoState> = _uiState.asStateFlow()


    init {
        getBookingInfo()
    }

    fun updatePhone(value: CharSequence?) {
        if (value == null) {
            _uiState.update { state ->
                (state as BookingInfoState.Content).copy(phone = EMPTY_VALUE, isPhoneValid = false)
            }
        } else {
            if (isValidPhone(value = value)) {
                _uiState.update { state ->
                    (state as BookingInfoState.Content).copy(
                        phone = getPhone(value.toString()), isPhoneValid = true
                    )
                }
            } else {
                _uiState.update { state ->
                    (state as BookingInfoState.Content).copy(
                        phone = getPhone(value.toString()), isPhoneValid = false
                    )
                }
            }
        }
    }

    fun updatePhoneColorField() {
        val isPhoneValid = (_uiState.value as BookingInfoState.Content).isPhoneValid
        if (isPhoneValid) {
            updatePhoneColorField(colorField = ColorField.DEFAULT)
        } else {
            updatePhoneColorField(colorField = ColorField.ERROR)
        }
    }

    fun updatePhoneColorField(colorField: ColorField) {
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(
                phoneColorIntField = colorField.colorInt
            )
        }
    }

    fun updateEmail(value: CharSequence?) {
        if (value == null) {
            _uiState.update { state ->
                (state as BookingInfoState.Content).copy(email = EMPTY_VALUE, isEmailValid = false)
            }
        } else {
            if (isValidEmail(value = value)) {
                _uiState.update { state ->
                    (state as BookingInfoState.Content).copy(
                        email = value.toString(), isEmailValid = true
                    )
                }
            } else {
                _uiState.update { state ->
                    (state as BookingInfoState.Content).copy(
                        email = value.toString(), isEmailValid = false
                    )
                }
            }
        }
    }

    fun updateEmailColorField() {
        val isEmailValid = (_uiState.value as BookingInfoState.Content).isEmailValid
        if (isEmailValid) {
            updateEmailColorField(colorField = ColorField.DEFAULT)
        } else {
            updateEmailColorField(colorField = ColorField.ERROR)
        }
    }

    fun updateEmailColorField(colorField: ColorField) {
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(
                emailColorIntField = colorField.colorInt
            )
        }
    }

    fun controlDisplayTouristDetails(position: Int) {
        val touristList = (uiState.value as BookingInfoState.Content).touristList
        val updatedTourist = touristList[position].copy(
            isShowDetails = !touristList[position].isShowDetails
        )
        val updatedTouristList = touristList.toMutableList().apply {
            this[position] = updatedTourist
        }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = updatedTouristList)
        }
    }

    fun updateTouristList() {
        _uiState.update { state ->
            val touristList = (state as BookingInfoState.Content).touristList
            if (touristList.size < TouristUi.MAX_ORDINAL_NUMBER) {
                val touristMutableList = touristList.toMutableList()
                touristMutableList.add(getTouristDefault(ordinalNumber = touristList.size))
                state.copy(touristList = touristMutableList)
            } else {
                state
            }
        }
    }

    fun updateFirstNameTouristList(
        value: CharSequence?,
        position: Int
    ) {
        val firstName = value ?: EMPTY_VALUE
        val tourist = (uiState.value as BookingInfoState.Content).touristList[position]
            .copy(firstName = firstName.toString())
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[position] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    fun updateFirstNameFirstTouristColorField() {
        val firstName =
            (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX].firstName
        if (firstName.isNotEmpty()) {
            updateFirstNameFirstTouristColorField(colorField = ColorField.DEFAULT)
        } else {
            updateFirstNameFirstTouristColorField(colorField = ColorField.ERROR)
        }
    }

    fun updateFirstNameFirstTouristColorField(colorField: ColorField) {
        val tourist = (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX]
            .copy(firstNameColorIntField = colorField.colorInt)
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[FIRST_INDEX] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    fun updateLastNameTouristList(
        value: CharSequence?,
        position: Int
    ) {
        val lastName = value ?: EMPTY_VALUE
        val tourist = (uiState.value as BookingInfoState.Content).touristList[position]
            .copy(lastName = lastName.toString())
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[position] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    fun updateLastNameFirstTouristColorField() {
        val lastName = (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX].lastName
        if (lastName.isNotEmpty()) {
            updateLastNameFirstTouristColorField(colorField = ColorField.DEFAULT)
        } else {
            updateLastNameFirstTouristColorField(colorField = ColorField.ERROR)
        }
    }

    fun updateLastNameFirstTouristColorField(colorField: ColorField) {
        val tourist = (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX]
            .copy(lastNameColorIntField = colorField.colorInt)
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[FIRST_INDEX] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    fun updateBirthdateTouristList(
        value: CharSequence?,
        position: Int
    ) {
        val birthdate = value ?: EMPTY_VALUE
        val tourist = (uiState.value as BookingInfoState.Content).touristList[position]
            .copy(birthdate = birthdate.toString())
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[position] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    fun updateBirthdateFirstTouristColorField() {
        val birthdate =
            (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX].birthdate
        if (birthdate.isNotEmpty()) {
            updateBirthdateFirstTouristColorField(colorField = ColorField.DEFAULT)
        } else {
            updateBirthdateFirstTouristColorField(colorField = ColorField.ERROR)
        }
    }

    fun updateBirthdateFirstTouristColorField(colorField: ColorField) {
        val tourist = (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX]
            .copy(birthdateColorIntField = colorField.colorInt)
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[FIRST_INDEX] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    fun updateCitizenshipTouristList(
        value: CharSequence?,
        position: Int
    ) {
        val citizenship = value ?: EMPTY_VALUE
        val tourist = (uiState.value as BookingInfoState.Content).touristList[position]
            .copy(citizenship = citizenship.toString())
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[position] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    fun updateCitizenshipFirstTouristColorField() {
        val citizenship =
            (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX].citizenship
        if (citizenship.isNotEmpty()) {
            updateCitizenshipFirstTouristColorField(colorField = ColorField.DEFAULT)
        } else {
            updateCitizenshipFirstTouristColorField(colorField = ColorField.ERROR)
        }
    }

    fun updateCitizenshipFirstTouristColorField(colorField: ColorField) {
        val tourist = (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX]
            .copy(citizenshipColorIntField = colorField.colorInt)
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[FIRST_INDEX] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    fun updateNumberInterPassportTouristList(
        value: CharSequence?,
        position: Int
    ) {
        val numberInterPassport = value ?: EMPTY_VALUE
        val tourist = (uiState.value as BookingInfoState.Content).touristList[position]
            .copy(numberInterPassport = numberInterPassport.toString())
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[position] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    fun updateNumberInterPassportTouristColorField() {
        val numberInterPassport =
            (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX]
                .numberInterPassport
        if (numberInterPassport.isNotEmpty()) {
            updateNumberInterPassportTouristColorField(colorField = ColorField.DEFAULT)
        } else {
            updateNumberInterPassportTouristColorField(colorField = ColorField.ERROR)
        }
    }

    fun updateNumberInterPassportTouristColorField(colorField: ColorField) {
        val tourist = (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX]
            .copy(numberInterPassportColorIntField = colorField.colorInt)
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[FIRST_INDEX] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    fun updateValidityPeriodInterPassportTouristList(
        value: CharSequence?,
        position: Int
    ) {
        val validityPeriodInterPassport = value ?: EMPTY_VALUE
        val tourist = (uiState.value as BookingInfoState.Content).touristList[position]
            .copy(validityPeriodInterPassport = validityPeriodInterPassport.toString())
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[position] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    fun updateValidityPeriodInterPassportFirstTouristColorField() {
        val validityPeriodInterPassport =
            (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX]
                .validityPeriodInterPassport
        if (validityPeriodInterPassport.isNotEmpty()) {
            updateValidityPeriodInterPassportFirstTouristColorField(colorField = ColorField.DEFAULT)
        } else {
            updateValidityPeriodInterPassportFirstTouristColorField(colorField = ColorField.ERROR)
        }
    }

    fun updateValidityPeriodInterPassportFirstTouristColorField(colorField: ColorField) {
        val tourist = (uiState.value as BookingInfoState.Content).touristList[FIRST_INDEX]
            .copy(validityPeriodInterPassportColorIntField = colorField.colorInt)
        val touristList = (uiState.value as BookingInfoState.Content).touristList.toMutableList()
            .apply { this[FIRST_INDEX] = tourist }
        _uiState.update { state ->
            (state as BookingInfoState.Content).copy(touristList = touristList)
        }
    }

    private fun getBookingInfo() {
        viewModelScope.launch {
            val loadingResult = getBookingInfoUseCase()
            handleLoadingResult(loadingResult = loadingResult)
        }
    }

    private fun handleLoadingResult(loadingResult: LoadingResult<BookingInfo>) {
        when (loadingResult) {
            is LoadingResult.Loading -> Unit
            is LoadingResult.Success -> reduce(loadingResult = loadingResult)
            is LoadingResult.Error -> reduce(loadingResult = loadingResult)
        }
    }

    private fun reduce(loadingResult: LoadingResult.Success<BookingInfo>) {
        _uiState.update {
            BookingInfoState.Content(
                bookingInfo = bookingInfoUiConverter.mapEntityToUiModel(
                    entity = loadingResult.value
                ),
                phone = EMPTY_VALUE,
                isPhoneValid = false,
                phoneColorIntField = ColorField.DEFAULT.colorInt,
                email = EMPTY_VALUE,
                isEmailValid = false,
                emailColorIntField = ColorField.DEFAULT.colorInt,
                touristList = listOf(getTouristDefault(ordinalNumber = FIRST_INDEX))
            )
        }
    }

    private fun getTouristDefault(ordinalNumber: Int): TouristUi {
        return TouristUi(
            ordinalNumber = ordinalNumber,
            isShowDetails = true,
            firstName = EMPTY_VALUE,
            firstNameColorIntField = ColorField.DEFAULT.colorInt,
            lastName = EMPTY_VALUE,
            lastNameColorIntField = ColorField.DEFAULT.colorInt,
            birthdate = EMPTY_VALUE,
            birthdateColorIntField = ColorField.DEFAULT.colorInt,
            citizenship = EMPTY_VALUE,
            citizenshipColorIntField = ColorField.DEFAULT.colorInt,
            numberInterPassport = EMPTY_VALUE,
            numberInterPassportColorIntField = ColorField.DEFAULT.colorInt,
            validityPeriodInterPassport = EMPTY_VALUE,
            validityPeriodInterPassportColorIntField = ColorField.DEFAULT.colorInt,
        )
    }

    private fun getPhone(maskedValue: String): String {
        val regex = Regex("\\d")
        val matches = regex.findAll(maskedValue).toList()
        return matches.subList(1, matches.size).joinToString(separator = "") { it.value }
    }

    private fun reduce(loadingResult: LoadingResult.Error) {
        _uiState.update { BookingInfoState.Error(message = loadingResult.message) }
    }

    companion object {

        private const val FIRST_INDEX = 0
        private const val EMPTY_VALUE = ""
    }
}