package com.masum.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.masum.demo.data.DataResource
import com.masum.demo.data.FactResponse
import com.masum.demo.network.ApiService
import javax.inject.Inject
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

class FactDataViewModel @Inject constructor(val apiService: ApiService) : ViewModel() {

    val allFactList: MediatorLiveData<DataResource<FactResponse>> =
        MediatorLiveData()

    fun getFactList() {
        allFactList.value = DataResource.loading()
        val source: LiveData<DataResource<FactResponse>> =
            LiveDataReactiveStreams.fromPublisher(
                apiService.getFactData()
                    .onErrorReturn {
                        val user = FactResponse()
                        user.all = null
                        user
                    }
                    .map(Function<FactResponse, DataResource<FactResponse>> { data ->
                        if (data.all == null) {
                            DataResource.error("Something went wrong")
                        } else {
                            DataResource.success(data)
                        }
                    })
                    .subscribeOn(Schedulers.io())
            )

        allFactList.addSource(
            source, Observer { data ->
                allFactList.value = data
            }
        )
    }
}