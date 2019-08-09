package com.example.advertpal.models.wallpost

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class PostResponseWrapper(

    @SerializedName("response")
    @Expose
    var response: PostResponse? = null
)