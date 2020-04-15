package com.example.sklepallegro.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sklepallegro.network.Offer

class DetailViewModelFactory(
    private val offer: Offer,
    private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(offer, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
