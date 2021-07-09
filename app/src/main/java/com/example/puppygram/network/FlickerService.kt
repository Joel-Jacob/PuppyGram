package com.example.puppygram.network

import com.example.puppygram.models.FlickerResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerService {

    @GET("/services/feeds/photos_public.gne")
    fun getPuppies(
        @Query("tags") tags: String,
        @Query("format") format: String,
        @Query("nojsoncallback") type: Int
    ): Observable<FlickerResponse>
}