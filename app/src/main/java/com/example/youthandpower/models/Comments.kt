package com.example.youthandpower.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class Comments {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("newsId")
    @Expose
    var newsId: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("email")
    @Expose
    var email: String? = null

    @SerializedName("content")
    @Expose
    var content: String? = null

    @SerializedName("date")
    @Expose
    var date: String? = null
}