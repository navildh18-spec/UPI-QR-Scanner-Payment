package com.upi.qrscanner.ui

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class PaymentViewModel : ViewModel() {
    
    private val _paymentState = MutableStateFlow<PaymentState>(PaymentState.Idle)
    val paymentState: StateFlow<PaymentState> = _paymentState

    sealed class PaymentState {
        object Idle : PaymentState()
        object Scanning : PaymentState()
        object Processing : PaymentState()
        data class Success(val message: String) : PaymentState()
        data class Error(val message: String) : PaymentState()
    }

    fun updatePaymentState(state: PaymentState) {
        _paymentState.value = state
    }
}
