package com.masum.demo.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Name  : Serializable {
    @SerializedName("first")
    @Expose
    var first: String? = null

    @SerializedName("last")
    @Expose
    var last: String? = null
}