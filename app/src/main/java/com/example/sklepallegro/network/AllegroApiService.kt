package com.example.sklepallegro.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
    import retrofit2.http.GET

private const val BASE_URL = "https://private-987cdf-allegromobileinterntest.apiary-mock.com/allegro/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getProperties] method
 */
interface AllegroApiService {
    @GET("offers")
    fun getOffers():
            Call<Offers>
}

object AllegroApi {
    val retrofitService : AllegroApiService by lazy { retrofit.create(AllegroApiService::class.java) }
}
