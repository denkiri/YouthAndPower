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

import com.example.youthandpower.models.CategoryData
class CategoriesRepository (application: Application) {
    private val context: Context
    val categoriesObservable = MutableLiveData<Resource<CategoryData>>()
    init {
        context =application.applicationContext
    }
    fun getCategories() {
        setIsLoading()

        if (NetworkUtils.isConnected(context)) {
            execute()
        } else {
            setIsError(context.getString(R.string.no_connection))
        }
    }
    private fun execute() {
        GlobalScope.launch(context = Dispatchers.Main) {
            val call = RequestService.getService("").Categories()
            call.enqueue(object : Callback<CategoryData> {
                override fun onFailure(call: Call<CategoryData>?, t: Throwable?) {
                    setIsError(t.toString())
                }

                override fun onResponse(call: Call<CategoryData>?, response: Response<CategoryData>?) {
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
        categoriesObservable.postValue(Resource.loading(null))
    }

    private fun setIsSuccessful(parameters: CategoryData) {
        categoriesObservable.postValue(Resource.success(parameters))
    }

    private fun setIsError(message: String) {
        categoriesObservable.postValue(Resource.error(message, null))
    }
}