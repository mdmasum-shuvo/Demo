package com.masum.demo.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class FactResponse  : Serializable {
    @SerializedName("all")
    @Expose
    var all: List<All>? = null
}