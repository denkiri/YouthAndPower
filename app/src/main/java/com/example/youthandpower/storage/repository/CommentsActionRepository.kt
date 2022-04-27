package com.example.youthandpower.storage.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.youthandpower.R
import com.example.youthandpower.custom.Resource
import com.example.youthandpower.models.CommentsData
import com.example.youthandpower.network.NetworkUtils
import com.example.youthandpower.network.RequestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class CommentsActionRepository (application: Application) {
    val commentActionObservable = MutableLiveData<Resource<CommentsData>>()
    private val context: Context
    init {
        context =application.applicationContext
    }
    fun comment(newsId:String,name:String,email:String,content:String) {
        setIsLoading(Observable. ADD_COMMENT)

        if (NetworkUtils.isConnected(context)) {
            addComment(newsId,name,email,content)
        } else {
            setIsError(Observable.ADD_COMMENT, context.getString(R.string.no_connection))
        }
    }
    fun addComment(commentId:String,name:String,email:String,content:String){
        GlobalScope.launch(context = Dispatchers.Main) {
            val call = RequestService.getService("").addComment(commentId,name,email,content)
            call.enqueue(object : Callback<CommentsData> {
                override fun onFailure(call: Call<CommentsData>?, t: Throwable?) {
                    setIsError(Observable.ADD_COMMENT, t.toString())
                }
                override fun onResponse(call: Call<CommentsData>?, response: Response<CommentsData>?) {
                    if (response != null) {
                        if (response.isSuccessful) {
                            if (response.body()?.status == 1) {
                                setIsSuccessful(Observable.ADD_COMMENT,response.body()!!)
                            } else {
                                response.body()?.message?.let { setIsError(Observable.ADD_COMMENT,it) }
                            }
                        }
                        else {
                            setIsError(Observable.ADD_COMMENT, response.toString())
                        }
                    } else {
                        setIsError(Observable.ADD_COMMENT, "Error Loading In")
                    }
                }
            })
        }

    }

    enum class Observable {
        ADD_COMMENT

    }

    private fun setIsLoading(observable: Observable) {
        when(observable) {
            Observable.  ADD_COMMENT-> commentActionObservable.postValue(Resource.loading(null))

        }
    }
    private fun <T> setIsSuccessful(observable: Observable, data: T?) {
        when (observable) {
            Observable.ADD_COMMENT -> commentActionObservable.postValue(Resource.success(data as CommentsData))

        }
    }
    private fun setIsError(observable: Observable, message: String) {
        when (observable) {
            Observable.ADD_COMMENT -> commentActionObservable.postValue(Resource.error(message, null))

        }
    }
}