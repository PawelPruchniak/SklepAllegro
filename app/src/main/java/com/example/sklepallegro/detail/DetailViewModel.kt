package com.example.sklepallegro.detail

import android.app.Application
import androidx.lifecycle.*
import com.example.sklepallegro.network.Offer

/**
 * The [ViewModel] that is associated with the [DetailFragment].
 */
class DetailViewModel(offer: Offer, app: Application) : AndroidViewModel(app) {
    private val _selectedOffer = MutableLiveData<Offer>()

    // The external LiveData for the SelectedProperty
    val selectedOffer: LiveData<Offer>
        get() = _selectedOffer

    // Initialize the _selectedProperty MutableLiveData
    init {
        _selectedOffer.value = offer
    }
}
