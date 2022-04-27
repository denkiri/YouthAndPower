package com.example.youthandpower.models
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class News {
    @SerializedName("news_id")
    @Expose
    var newsId: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("content")
    @Expose
    var content: String? = null

    @SerializedName("author_id")
    @Expose
    var authorId: Int? = null

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("feature")
    @Expose
    var feature: Int? = null

    @SerializedName("approve")
    @Expose
    var approve: Int? = null

    @SerializedName("category")
    @Expose
    var category: Int? = null

    @SerializedName("views")
    @Expose
    var views: Int? = null

    @SerializedName("comments")
    @Expose
    var comments: Int? = null

    @SerializedName("img")
    @Expose
    var img: String? = null

    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null

    @SerializedName("video_id")
    @Expose
    var videoId: String? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("names")
    @Expose
    var author: String? = null
}