package com.example.sklepallegro.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sklepallegro.network.AllegroApi
import com.example.sklepallegro.network.Offer
import com.example.sklepallegro.network.Offers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the status of the most recent request
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the request status String
    val response: LiveData<String>
        get() = _response

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
            var getPropertiesDeferred = AllegroApi.retrofitService.getOffers()
            try {
                var listResult = getPropertiesDeferred.await()
                _response.value = "Success: Got ${listResult.offers.size} Allegro offers"
            } catch (t:Throwable){
                _response.value = "Failure: " +  t.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
