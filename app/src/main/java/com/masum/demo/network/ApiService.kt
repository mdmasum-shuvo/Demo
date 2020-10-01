package com.masum.demo.network

import com.masum.demo.data.FactResponse
import io.reactivex.Flowable
import retrofit2.http.GET

interface ApiService {

    @GET(HTTP_PARAM.FACTS)
    fun getFactData(): Flowable<FactResponse>
}