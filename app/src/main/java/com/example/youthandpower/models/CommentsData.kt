package com.example.youthandpower.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class CommentsData {
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("comments")
    @Expose
    var comments: List<Comments>? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}