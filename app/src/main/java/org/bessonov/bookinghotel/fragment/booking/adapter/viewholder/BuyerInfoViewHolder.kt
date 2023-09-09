package org.bessonov.bookinghotel.fragment.booking.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import org.bessonov.bookinghotel.R
import org.bessonov.bookinghotel.databinding.BuyerInfoBinding
import org.bessonov.bookinghotel.util.BuyerField
import org.bessonov.bookinghotel.util.changeColorBg
import org.bessonov.bookinghotel.util.isValidEmail
import org.bessonov.bookinghotel.util.isValidPhone

class BuyerInfoViewHolder(
    val parent: ViewGroup,
    private val onBuyerChanged: ((CharSequence?, BuyerField) -> Unit)?,
    private val onBuyerFocusChange: ((Boolean, BuyerField) -> Unit)?,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.buyer_info,
        parent,
        false
    )
) {

    val binding = BuyerInfoBinding.bind(itemView)

    init {

        setPhoneTextChanged()
        setEmailTextChanged()
        setPhoneFocusChangeListener()
        setEmailFocusChangeListener()
    }

    fun onBind(
        phone: String,
        @ColorRes phoneColorIntField: Int,
        email: String,
        @ColorRes emailColorIntField: Int
    ) {
        setContent(
            phone = phone,
            phoneColorIntField = phoneColorIntField,
            email = email,
            emailColorIntField = emailColorIntField
        )
    }

    fun setPhoneErrorColor() {
        binding.phoneNumberTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield_error,
                null
            )
        )
    }

    fun setEmailErrorColor() {
        binding.emailTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield_error,
                null
            )
        )
    }

    private fun setPhoneTextChanged() {
        binding.phoneNumberEdt.doOnTextChanged { text, _, _, _ ->
            if (binding.phoneNumberEdt.isFocused) {
                onBuyerChanged?.invoke(text, BuyerField.PHONE)
            }
        }
    }

    private fun setEmailTextChanged() {
        binding.emailEdt.doOnTextChanged { text, _, _, _ ->
            if (binding.emailEdt.isFocused) {
                onBuyerChanged?.invoke(text, BuyerField.EMAIL)
            }
        }
    }

    private fun setPhoneFocusChangeListener() {
        binding.phoneNumberEdt.setOnFocusChangeListener { _, hasFocus ->
            onBuyerFocusChange?.invoke(hasFocus, BuyerField.PHONE)
            if ((!hasFocus)
                    .and(
                        (binding.phoneNumberEdt.text == null)
                            .or(!isValidPhone(binding.phoneNumberEdt.text.toString()))
                    )
            ) {
                setPhoneErrorColor()
            } else {
                setPhoneDefaultColor()
            }
        }
    }

    private fun setEmailFocusChangeListener() {
        binding.emailEdt.setOnFocusChangeListener { _, hasFocus ->
            onBuyerFocusChange?.invoke(hasFocus, BuyerField.EMAIL)
            if ((!hasFocus)
                    .and(
                        binding.emailEdt.text.isNullOrEmpty()
                            .or(!isValidEmail(binding.emailEdt.text.toString()))
                    )
            ) {
                setEmailErrorColor()
            } else {
                setEmailDefaultColor()
            }
        }
    }

    private fun setPhoneDefaultColor() {
        binding.phoneNumberTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield,
                null
            )
        )
    }

    private fun setEmailDefaultColor() {
        binding.emailTil.changeColorBg(
            colorTo = itemView.resources.getColor(
                R.color.md_theme_light_textfield,
                null
            )
        )
    }

    private fun setContent(
        phone: String,
        @ColorRes phoneColorIntField: Int,
        email: String,
        @ColorRes emailColorIntField: Int
    ) {
        binding.phoneNumberEdt.setText(phone)
        binding.phoneNumberTil.boxBackgroundColor = itemView.resources
            .getColor(phoneColorIntField, null)
        binding.emailEdt.setText(email)
        binding.emailTil.boxBackgroundColor = itemView.resources
            .getColor(emailColorIntField, null)
    }
}