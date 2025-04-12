package com.proNutriLog.proteinCalculator.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proNutriLog.proteinCalculator.data.model.KrogerProductResponse
import com.proNutriLog.proteinCalculator.data.repository.KrogerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KrogerViewModel @Inject constructor(
    private val repository: KrogerRepository
) : ViewModel() {

    private val _products = MutableLiveData<KrogerProductResponse?>()
    val products: MutableLiveData<KrogerProductResponse?> = _products

    @RequiresApi(Build.VERSION_CODES.O)
    fun search(term: String, locationId: String) {
        viewModelScope.launch {
            try {
                val result = repository.searchProducts(term, locationId)
                _products.value = result
            } catch (e: Exception) {
                // Log error and show fallback
                e.printStackTrace()
                _products.value = null // Optional fallback
            }
        }
    }

}

