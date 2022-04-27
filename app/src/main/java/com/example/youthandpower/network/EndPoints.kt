package com.example.youthandpower.network
import com.example.youthandpower.models.CategoryData
import com.example.youthandpower.models.CommentsData
import com.example.youthandpower.models.NewsData
import retrofit2.Call
import retrofit2.http.*
interface EndPoints {
    @GET("news/categories.php")
    fun Categories(): Call<CategoryData>
    @GET("news/news.php")
    fun News(): Call<NewsData>
    @FormUrlEncoded
    @POST("news/comments.php")
    fun comments(@Field("cid")cid:String?): Call<CommentsData>
    @FormUrlEncoded
    @POST("news/addComment.php")
    fun addComment(@Field("cid")code:String?,@Field("name")name:String?,@Field("email")email:String?,@Field("content")content:String?): Call<CommentsData>

}