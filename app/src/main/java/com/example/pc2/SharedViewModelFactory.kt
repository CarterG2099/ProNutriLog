package com.example.pc2

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SharedViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProteinCostViewModel::class.java)) {
            return ProteinCostViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
