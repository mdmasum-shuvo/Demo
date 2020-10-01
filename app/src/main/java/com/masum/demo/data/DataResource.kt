package com.masum.demo.data

class DataResource<T>(val status: DataStatus, val data: T?, val message: String?) {

    enum class DataStatus {
        SUCCESS, ERROR, LOADING
    }

    companion object {
       open fun <T> success(data: T): DataResource<T> {
            return DataResource(DataStatus.SUCCESS, data, null)
        }

        open fun <T> error(msg: String): DataResource<T> {
            return DataResource(DataStatus.ERROR, null, msg)
        }

        open fun <T> loading(): DataResource<T> {
            return DataResource(DataStatus.LOADING, null, null)
        }


    }

}