package com.example.sklepallegro.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://private-987cdf-allegromobileinterntest.apiary-mock.com/allegro/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getOffers] method
 */
interface AllegroApiService {
    @GET("offers")
    fun getOffers():
            Deferred<Offers>
}

object AllegroApi {
    val retrofitService: AllegroApiService by lazy { retrofit.create(AllegroApiService::class.java) }
}
