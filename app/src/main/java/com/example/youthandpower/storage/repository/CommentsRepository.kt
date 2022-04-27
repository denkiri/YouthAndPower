package com.example.youthandpower.storage.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.example.youthandpower.R
import com.example.youthandpower.custom.Resource
import com.example.youthandpower.models.CommentsData
import com.example.youthandpower.models.NewsData
import com.example.youthandpower.network.NetworkUtils
import com.example.youthandpower.network.RequestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsRepository  (application: Application) {
    private val context: Context
    init {
        context =application.applicationContext
    }
    val commentsObservable = MutableLiveData<Resource<CommentsData>>()
    fun getComments(commentsId:String) {
        setIsLoading()

        if (NetworkUtils.isConnected(context)) {
            execute(commentsId)
        } else {
            setIsError(context.getString(R.string.no_connection))
        }
    }

    private fun execute(cid:String) {
        GlobalScope.launch(context = Dispatchers.Main) {
            val call = RequestService.getService("").comments(cid)
            call.enqueue(object : Callback<CommentsData> {
                override fun onFailure(call: Call<CommentsData>?, t: Throwable?) {
                    setIsError(t.toString())
                }

                override fun onResponse(call: Call<CommentsData>?, response: Response<CommentsData>?) {
                    if (response != null) {
                        if (response.isSuccessful) {
                            if (response.body()?.status == 1) {

                                setIsSuccessful(response.body()!!)
                            } else {
                                response.body()?.message?.let { setIsError(it) }
                            }
                        } else {
                            setIsError("Error Loading Data")
                        }
                    } else {
                        setIsError("Error Loading Data")
                    }
                }
            })
        }
    }
    private fun setIsLoading() {
        commentsObservable.postValue(Resource.loading(null))
    }

    private fun setIsSuccessful(parameters: CommentsData) {
        commentsObservable.postValue(Resource.success(parameters))
    }

    private fun setIsError(message: String) {
        commentsObservable.postValue(Resource.error(message, null))
    }

}