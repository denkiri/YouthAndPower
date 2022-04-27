package com.example.youthandpower.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class CategoryData {
    @SerializedName("error")
    @Expose
    var error: Boolean? = null

    @SerializedName("status")
    @Expose
    var status: Int? = null

    @SerializedName("categories")
    @Expose
    var categories: List<Category>? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}