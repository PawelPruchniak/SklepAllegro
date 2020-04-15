package com.example.sklepallegro.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sklepallegro.network.AllegroApi
import com.example.sklepallegro.network.Offer
import com.example.sklepallegro.network.Offers
import com.example.sklepallegro.network.Price
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

enum class AllegroApiStatus { LOADING, ERROR, DONE}

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<AllegroApiStatus>()

    // The external immutable LiveData for the request status String
    val status: LiveData<AllegroApiStatus>
        get() = _status

    private val _offers = MutableLiveData<List<Offer>>()

    val offers: LiveData<List<Offer>>
        get() = _offers

    private val _navigatetoSelectedOffer = MutableLiveData<Offer>()

    val navigatetoSelectedOffer: LiveData<Offer>
        get() = _navigatetoSelectedOffer

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getAllegroOffers() on init so we can display status immediately.
     */
    init {
        getAllegroOffers()
    }

    /**
     * Sets the value of the status LiveData to the Allegro Api status
     */
    private fun getAllegroOffers() {

        coroutineScope.launch {
            val getPropertiesDeferred = AllegroApi.retrofitService.getOffers()
            try {
                _status.value = AllegroApiStatus.LOADING
                val listResult = getPropertiesDeferred.await()

                // Sorting and filtering offers by price.amount
                _offers.value =
                    listResult.offers.filter{ it.price.amount in 50.0..1000.0 }
                        .sortedBy { it.price.amount }

                _status.value = AllegroApiStatus.DONE
            } catch (t:Throwable){
                _status.value = AllegroApiStatus.ERROR
                _offers.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun displayOfferDetails(offer: Offer) {
        _navigatetoSelectedOffer.value = offer
    }

    fun displayOfferDetailsComplete() {
        _navigatetoSelectedOffer.value = null
    }
}
