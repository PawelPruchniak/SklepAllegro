package com.example.sklepallegro.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sklepallegro.network.AllegroApi
import com.example.sklepallegro.network.Offer
import com.example.sklepallegro.network.Offers
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

    /**
     * Call getAllegroOffersProperties() on init so we can display status immediately.
     */
    init {
        getAllegroOffers()
    }

    /**
     * Sets the value of the status LiveData to the Mars API status.
     */
    private fun getAllegroOffers() {
        AllegroApi.retrofitService.getOffers().enqueue(object: Callback<Offers> {
            override fun onFailure(call: Call<Offers>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }

            override fun onResponse(call: Call<Offers>, response: Response<Offers>) {
                _response.value = "Success: Got ${response.body()?.offers?.size} Allegro offers"
            }
        })
    }
}
