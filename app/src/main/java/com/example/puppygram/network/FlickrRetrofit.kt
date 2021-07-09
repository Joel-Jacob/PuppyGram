package com.example.puppygram.network

import com.example.puppygram.models.FlickerResponse
import com.example.puppygram.utils.Constants.BASE_URL
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object FlickrRetrofit {
    private var flickerService: FlickerService

    init {
        flickerService = getService(getInstance())
    }

    private fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun getService(retrofit: Retrofit): FlickerService {
        return retrofit.create(FlickerService::class.java)
    }

    fun getPuppies(tags: String): Observable<FlickerResponse> {
        return flickerService.getPuppies(tags, "json", 1)
    }
}