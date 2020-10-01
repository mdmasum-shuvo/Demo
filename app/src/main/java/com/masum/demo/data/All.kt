package com.masum.demo.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class All :Serializable{
    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("text")
    @Expose
    var text: String? = null

    @SerializedName("user")
    @Expose
    var user: User? = null

    @SerializedName("upvotes")
    @Expose
    var upvotes: Int? = null

    @SerializedName("userUpvoted")
    @Expose
    var userUpvoted: String? = null
}