package com.example.puppygram.network

import com.example.puppygram.models.FlickerResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FlickerRepository {
    fun getPuppies(tags: String): Observable<FlickerResponse> {
        return FlickrRetrofit
            .getPuppies(tags)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}