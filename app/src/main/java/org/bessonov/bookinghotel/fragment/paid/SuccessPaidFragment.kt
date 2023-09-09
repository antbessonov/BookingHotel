package org.bessonov.bookinghotel.fragment.paid

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
import org.bessonov.bookinghotel.databinding.FragmentSuccessPaidBinding
import org.bessonov.bookinghotel.util.fadeIn

@AndroidEntryPoint
class SuccessPaidFragment : Fragment(R.layout.fragment_success_paid) {

    private val binding: FragmentSuccessPaidBinding by viewBinding()

    private val viewModel by lazy {
        ViewModelProvider(this)[SuccessPaidViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setNavigationBackClickListener()
        observeViewModelState()
    }

    private fun setNavigationBackClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
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

    private fun handleState(state: SuccessPaidState) {
        when (state) {
            is SuccessPaidState.Loading -> reduce(state = state)
            is SuccessPaidState.Content -> reduce(state = state)
            is SuccessPaidState.Error -> reduce(state = state)
        }
    }

    private fun reduce(state: SuccessPaidState.Loading) {
        showLoadingProgress()
    }

    private fun showLoadingProgress() {
        binding.loadingProgress.visibility = View.VISIBLE
    }

    private fun hideLoadingProgress() {
        binding.loadingProgress.visibility = View.GONE
    }

    private fun reduce(state: SuccessPaidState.Content) {
        hideLoadingProgress()
        showContentLayout()
        setPaidDescription(state = state)
        setOkClickListener()
    }

    private fun showContentLayout() {
        binding.titleTv.fadeIn()
        binding.descriptionTv.fadeIn()
        binding.buttonContainer.fadeIn()
        binding.okBtn.fadeIn()
    }

    private fun setPaidDescription(state: SuccessPaidState.Content) {
        binding.descriptionTv.text = getString(
            R.string.success_paid_description,
            state.numberOrder
        )
    }

    private fun setOkClickListener() {
        binding.okBtn.setOnClickListener {
            findNavController().navigate(
                R.id.action_navigation_success_paid_to_navigation_hotel
            )
        }
    }

    private fun reduce(state: SuccessPaidState.Error) {
        hideLoadingProgress()
    }
}