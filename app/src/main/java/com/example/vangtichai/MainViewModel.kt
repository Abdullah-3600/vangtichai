package com.example.vangtichai

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class MainViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    companion object {
        private const val KEY_AMOUNT_STRING = "key_amount_string"
        private const val MAX_DIGITS = 9
    }

    private val _amountText = MutableLiveData<String>()
    val amountText: LiveData<String> get() = _amountText

    private val _changeMap = MutableLiveData<Map<Int, Long>>()
    val changeMap: LiveData<Map<Int, Long>> get() = _changeMap

    init {
        val initialAmount = savedStateHandle.get<String>(KEY_AMOUNT_STRING) ?: ""
        updateAmount(initialAmount)
    }

    fun onDigitClicked(digit: Int) {
        val current = _amountText.value ?: ""
        val newAmount = if (current.isEmpty()) {
            digit.toString()
        } else if (current.length < MAX_DIGITS) {
            current + digit
        } else {
            current
        }
        updateAmount(newAmount)
    }

    fun onClearClicked() {
        updateAmount("")
    }

    private fun updateAmount(newAmountStr: String) {
        savedStateHandle[KEY_AMOUNT_STRING] = newAmountStr
        _amountText.value = newAmountStr

        val amountVal = newAmountStr.toLongOrNull() ?: 0L
        _changeMap.value = NoteCalculator.calculateChange(amountVal)
    }
}
