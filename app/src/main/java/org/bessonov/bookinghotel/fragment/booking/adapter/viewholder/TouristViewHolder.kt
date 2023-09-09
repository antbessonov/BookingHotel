package org.bessonov.bookinghotel.fragment.booking.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.TouristItemBinding
import org.bessonov.bookinghotel.model.TouristUi
import org.bessonov.bookinghotel.util.TouristField
import org.bessonov.bookinghotel.util.changeColorBg

class TouristViewHolder(
    val parent: ViewGroup,
    private val onShowTouristDetailsClick: ((Int) -> Unit)?,
    private val onTouristChanged: ((CharSequence?, Int, TouristField) -> Unit)?,
    private val onTouristFocusChange: ((Boolean, Int, TouristField) -> Unit)?,
) :
    RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.tourist_item,
            parent,
            false
        )
    ) {
    val binding = TouristItemBinding.bind(itemView)

    init {

        setShowDetailClickListener()
        setFirstNameTextChanged()
        setLastNameTextChanged()
        setBirthdateTextChanged()
        setCitizenshipTextChanged()
        setNumberInternationalPassportTextChanged()
        setValidityPeriodInternationalPassportTextChanged()
        setFirstNameFocusChangeListener()
        setLastNameFocusChangeListener()
        setBirthdateFocusChangeListener()
        setCitizenshipFocusChangeListener()
        setNumberInternationalPassportFocusChangeListener()
        setValidityPeriodInternationalPassportFocusChangeListener()

    }

    fun onBind(tourist: TouristUi) {
        setContent(tourist = tourist)
    }

    fun setFirstNameFirstTouristErrorColor() {
        if (adapterPosition == START_TOURIST_POSITION) {
            setFirstNameErrorColor()
        }
    }

    fun setLastNameFirstTouristErrorColor() {
        if (adapterPosition == START_TOURIST_POSITION) {
            setLastNameErrorColor()
        }
    }

    fun setBirthdateFirstTouristErrorColor() {
        if (adapterPosition == START_TOURIST_POSITION) {
            setBirthdateErrorColor()
        }
    }

    fun setCitizenshipFirstTouristErrorColor() {
        if (adapterPosition == START_TOURIST_POSITION) {
            setCitizenshipErrorColor()
        }
    }

    fun setNumberInterPassportFirstTouristErrorColor() {
        if (adapterPosition == START_TOURIST_POSITION) {
            setNumberInternationalPassportErrorColor()
        }
    }

    fun setValidityPeriodInternationalPassportFirstTouristErrorColor() {
        if (adapterPosition == START_TOURIST_POSITION) {
            setValidityPeriodInternationalPassportErrorColor()
        }
    }

    private fun setShowDetailClickListener() {
        binding.showDetailsIv.setOnClickListener {
            onShowTouristDetailsClick?.invoke(adapterPosition - START_TOURIST_POSITION)
        }
    }

    private fun setFirstNameTextChanged() {
        binding.firstNameEdt.doOnTextChanged { text, _, _, _ ->
            if (binding.firstNameEdt.isFocused) {
                onTouristChanged?.invoke(
                    text,
                    adapterPosition - START_TOURIST_POSITION,
                    TouristField.FIRST_NAME
                )
            }
        }
    }

    private fun setLastNameTextChanged() {
        binding.lastNameEdt.doOnTextChanged { text, _, _, _ ->
            if (binding.lastNameEdt.isFocused) {
                onTouristChanged?.invoke(
                    text,
                    adapterPosition - START_TOURIST_POSITION,
                    TouristField.LAST_NAME
                )
            }
        }
    }

    private fun setBirthdateTextChanged() {
        binding.birthdateEdt.doOnTextChanged { text, _, _, _ ->
            if (binding.birthdateEdt.isFocused) {
                onTouristChanged?.invoke(
                    text,
                    adapterPosition - START_TOURIST_POSITION,
                    TouristField.BIRTHDATE
                )
            }
        }
    }

    private fun setCitizenshipTextChanged() {
        binding.citizenshipEdt.doOnTextChanged { text, _, _, _ ->
            if (binding.citizenshipEdt.isFocused) {
                onTouristChanged?.invoke(
                    text,
                    adapterPosition - START_TOURIST_POSITION,
                    TouristField.CITIZENSHIP
                )
            }
        }
    }

    private fun setNumberInternationalPassportTextChanged() {
        binding.numberInternationalPassportEdt.doOnTextChanged { text, _, _, _ ->
            if (binding.numberInternationalPassportEdt.isFocused) {
                onTouristChanged?.invoke(
                    text,
                    adapterPosition - START_TOURIST_POSITION,
                    TouristField.NUMBER_INTER_PASSPORT
                )
            }
        }
    }

    private fun setValidityPeriodInternationalPassportTextChanged() {
        binding.validityPeriodInternationalPassportEdt.doOnTextChanged { text, _, _, _ ->
            if (binding.validityPeriodInternationalPassportEdt.isFocused) {
                onTouristChanged?.invoke(
                    text,
                    adapterPosition - START_TOURIST_POSITION,
                    TouristField.VALIDITY_PERIOD_PASSPORT
                )
            }
        }
    }

    private fun setFirstNameFocusChangeListener() {
        binding.firstNameEdt.setOnFocusChangeListener { _, hasFocus ->
            onTouristFocusChange?.invoke(hasFocus, adapterPosition, TouristField.FIRST_NAME)
            if ((!hasFocus)
                    .and(binding.firstNameEdt.text.isNullOrEmpty())
                    .and(adapterPosition == START_TOURIST_POSITION)
            ) {
                setFirstNameErrorColor()
            } else {
                setFirstNameDefaultColor()
            }
        }
    }

    private fun setFirstNameErrorColor() {
        binding.firstNameTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield_error,
                null
            )
        )
    }

    private fun setFirstNameDefaultColor() {
        binding.firstNameTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield,
                null
            )
        )
    }

    private fun setLastNameFocusChangeListener() {
        binding.lastNameEdt.setOnFocusChangeListener { _, hasFocus ->
            onTouristFocusChange?.invoke(hasFocus, adapterPosition, TouristField.LAST_NAME)
            if ((!hasFocus)
                    .and(binding.lastNameEdt.text.isNullOrEmpty())
                    .and(adapterPosition == START_TOURIST_POSITION)
            ) {
                setLastNameErrorColor()
            } else {
                setLastNameDefaultColor()
            }
        }
    }

    private fun setLastNameErrorColor() {
        binding.lastNameTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield_error,
                null
            )
        )
    }

    private fun setLastNameDefaultColor() {
        binding.lastNameTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield,
                null
            )
        )
    }

    private fun setBirthdateFocusChangeListener() {
        binding.birthdateEdt.setOnFocusChangeListener { _, hasFocus ->
            onTouristFocusChange?.invoke(hasFocus, adapterPosition, TouristField.BIRTHDATE)
            if ((!hasFocus)
                    .and(binding.birthdateEdt.text.isNullOrEmpty())
                    .and(adapterPosition == START_TOURIST_POSITION)
            ) {
                setBirthdateErrorColor()
            } else {
                setBirthdateDefaultColor()
            }
        }
    }

    private fun setBirthdateErrorColor() {
        binding.birthdateTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield_error,
                null
            )
        )
    }

    private fun setBirthdateDefaultColor() {
        binding.birthdateTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield,
                null
            )
        )
    }

    private fun setCitizenshipFocusChangeListener() {
        binding.citizenshipEdt.setOnFocusChangeListener { _, hasFocus ->
            onTouristFocusChange?.invoke(hasFocus, adapterPosition, TouristField.CITIZENSHIP)
            if ((!hasFocus)
                    .and(binding.citizenshipEdt.text.isNullOrEmpty())
                    .and(adapterPosition == START_TOURIST_POSITION)
            ) {
                setCitizenshipErrorColor()
            } else {
                setCitizenshipDefaultColor()
            }
        }
    }

    private fun setCitizenshipErrorColor() {
        binding.citizenshipTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield_error,
                null
            )
        )
    }

    private fun setCitizenshipDefaultColor() {
        binding.citizenshipTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield,
                null
            )
        )
    }

    private fun setNumberInternationalPassportFocusChangeListener() {
        binding.numberInternationalPassportEdt.setOnFocusChangeListener { _, hasFocus ->
            onTouristFocusChange?.invoke(
                hasFocus,
                adapterPosition,
                TouristField.NUMBER_INTER_PASSPORT
            )
            if ((!hasFocus)
                    .and(binding.numberInternationalPassportEdt.text.isNullOrEmpty())
                    .and(adapterPosition == START_TOURIST_POSITION)
            ) {
                setNumberInternationalPassportErrorColor()
            } else {
                setNumberInternationalPassportDefaultColor()
            }
        }
    }

    private fun setNumberInternationalPassportErrorColor() {
        binding.numberInternationalPassportTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield_error,
                null
            )
        )
    }

    private fun setNumberInternationalPassportDefaultColor() {
        binding.numberInternationalPassportTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield,
                null
            )
        )
    }

    private fun setValidityPeriodInternationalPassportFocusChangeListener() {
        binding.validityPeriodInternationalPassportEdt.setOnFocusChangeListener { _, hasFocus ->
            onTouristFocusChange?.invoke(
                hasFocus,
                adapterPosition,
                TouristField.VALIDITY_PERIOD_PASSPORT
            )
            if ((!hasFocus)
                    .and(binding.validityPeriodInternationalPassportEdt.text.isNullOrEmpty())
                    .and(adapterPosition == START_TOURIST_POSITION)
            ) {
                setValidityPeriodInternationalPassportErrorColor()
            } else {
                setValidityPeriodInternationalPassportDefaultColor()
            }
        }
    }

    private fun setValidityPeriodInternationalPassportErrorColor() {
        binding.validityPeriodInternationalPassportTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield_error,
                null
            )
        )
    }

    private fun setValidityPeriodInternationalPassportDefaultColor() {
        binding.validityPeriodInternationalPassportTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield,
                null
            )
        )
    }

    private fun setContent(tourist: TouristUi) {
        setOrdinalNumber(tourist = tourist)
        setShowDetailsIcon(tourist = tourist)
        showDetails(tourist = tourist)
        binding.firstNameEdt.setText(tourist.firstName)
        binding.firstNameTil.boxBackgroundColor = itemView.resources
            .getColor(tourist.firstNameColorIntField, null)
        binding.lastNameEdt.setText(tourist.lastName)
        binding.lastNameTil.boxBackgroundColor = itemView.resources
            .getColor(tourist.lastNameColorIntField, null)
        binding.birthdateEdt.setText(tourist.birthdate)
        binding.birthdateTil.boxBackgroundColor = itemView.resources
            .getColor(tourist.birthdateColorIntField, null)
        binding.citizenshipEdt.setText(tourist.citizenship)
        binding.citizenshipTil.boxBackgroundColor = itemView.resources
            .getColor(tourist.citizenshipColorIntField, null)
        binding.numberInternationalPassportEdt.setText(tourist.numberInterPassport)
        binding.numberInternationalPassportTil.boxBackgroundColor = itemView.resources
            .getColor(tourist.numberInterPassportColorIntField, null)
        binding.validityPeriodInternationalPassportEdt.setText(tourist.validityPeriodInterPassport)
        binding.validityPeriodInternationalPassportTil.boxBackgroundColor = itemView.resources
            .getColor(tourist.validityPeriodInterPassportColorIntField, null)
    }

    private fun setOrdinalNumber(tourist: TouristUi) {
        val resources = itemView.resources
        val ordinalNumber = resources
            .getStringArray(R.array.ordinals)[tourist.ordinalNumber]
            .replaceFirstChar(Char::uppercaseChar)
        binding.ordinalNumberTv.text = resources.getString(R.string.ordinals_sample, ordinalNumber)
    }

    private fun setShowDetailsIcon(tourist: TouristUi) {
        if (tourist.isShowDetails) {
            binding.showDetailsIv.setImageResource(R.drawable.arrow_up)
        } else {
            binding.showDetailsIv.setImageResource(R.drawable.arrow_down)
        }
    }

    private fun showDetails(tourist: TouristUi) {
        if (tourist.isShowDetails) {
            binding.firstNameTil.visibility = View.VISIBLE
            binding.lastNameTil.visibility = View.VISIBLE
            binding.birthdateTil.visibility = View.VISIBLE
            binding.citizenshipTil.visibility = View.VISIBLE
            binding.numberInternationalPassportTil.visibility = View.VISIBLE
            binding.validityPeriodInternationalPassportTil.visibility = View.VISIBLE
        } else {
            binding.firstNameTil.visibility = View.GONE
            binding.lastNameTil.visibility = View.GONE
            binding.birthdateTil.visibility = View.GONE
            binding.citizenshipTil.visibility = View.GONE
            binding.numberInternationalPassportTil.visibility = View.GONE
            binding.validityPeriodInternationalPassportTil.visibility = View.GONE
        }
    }

    companion object {

        private const val START_TOURIST_POSITION = 3
    }
}