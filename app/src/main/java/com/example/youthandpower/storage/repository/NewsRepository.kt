package com.example.youthandpower.storage.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.youthandpower.R
import com.example.youthandpower.custom.Resource
import com.example.youthandpower.models.News
import com.example.youthandpower.models.NewsData
import com.example.youthandpower.network.NetworkUtils
import com.example.youthandpower.network.RequestService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class NewsRepository (application: Application) {
    private val context: Context
    init {
        context =application.applicationContext
    }
    val newsObservable = MutableLiveData<Resource<NewsData>>()
    fun getNews() {
        setIsLoading()

        if (NetworkUtils.isConnected(context)) {
            execute()
        } else {
            setIsError(context.getString(R.string.no_connection))
        }
    }

    private fun execute() {
        GlobalScope.launch(context = Dispatchers.Main) {
            val call = RequestService.getService("").News()
            call.enqueue(object : Callback<NewsData> {
                override fun onFailure(call: Call<NewsData>?, t: Throwable?) {
                    setIsError(t.toString())
                }

                override fun onResponse(call: Call<NewsData>?, response: Response<NewsData>?) {
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
        newsObservable.postValue(Resource.loading(null))
    }

    private fun setIsSuccessful(parameters: NewsData) {
        newsObservable.postValue(Resource.success(parameters))
    }

    private fun setIsError(message: String) {
        newsObservable.postValue(Resource.error(message, null))
    }

}