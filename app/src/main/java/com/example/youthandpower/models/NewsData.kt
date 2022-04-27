package com.example.youthandpower.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class NewsData {
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("news")
    @Expose
    var news: List<News>? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}