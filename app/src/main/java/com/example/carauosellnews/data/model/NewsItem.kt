package com.example.carauosellnews.data.model


import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class NewsItem(
    @SerializedName("banner_url")
    var bannerUrl: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("id")
    var id: String? = null,
    @SerializedName("rank")
    var rank: Int? = null,
    @SerializedName("time_created")
    var timeCreated: Long? = null,
    @SerializedName("title")
    var title: String? = null
)