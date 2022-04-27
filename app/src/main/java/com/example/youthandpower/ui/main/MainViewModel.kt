package com.example.youthandpower.ui.main
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.youthandpower.custom.Resource
import com.example.youthandpower.models.CategoryData
import com.example.youthandpower.models.Comments
import com.example.youthandpower.models.CommentsData
import com.example.youthandpower.models.NewsData
import com.example.youthandpower.storage.repository.CategoriesRepository
import com.example.youthandpower.storage.repository.CommentsActionRepository
import com.example.youthandpower.storage.repository.CommentsRepository
import com.example.youthandpower.storage.repository.NewsRepository

class MainViewModel  (application: Application): AndroidViewModel(application){
    internal var newsRepository: NewsRepository
    internal var categoriesRepository:CategoriesRepository
    internal var commentsRepository:CommentsRepository
    internal var commentsActionRepository: CommentsActionRepository
    private  val categoryObservable=MediatorLiveData<Resource<CategoryData>>()
    private val newsObservable = MediatorLiveData<Resource<NewsData>>()
    private val commentsObservable= MediatorLiveData<Resource<CommentsData>>()
    private val commentsActionObservable= MediatorLiveData<Resource<CommentsData>>()
    init {
        newsRepository= NewsRepository(application)
        categoriesRepository= CategoriesRepository(application)
        commentsRepository= CommentsRepository(application)
        commentsActionRepository= CommentsActionRepository(application)
        newsObservable.addSource(newsRepository.newsObservable) { data -> newsObservable.setValue(data) }
        categoryObservable.addSource(categoriesRepository.categoriesObservable){data -> categoryObservable.setValue(data)}
        commentsObservable.addSource(commentsRepository.commentsObservable) { data -> commentsObservable.setValue(data) }
        commentsActionObservable.addSource(commentsActionRepository.commentActionObservable) { data -> commentsActionObservable.setValue(data) }

    }
    fun observeNews(): LiveData<Resource<NewsData>> {
        return newsObservable
    }
    fun observeCategories(): LiveData<Resource<CategoryData>> {
        return categoryObservable
    }
    fun getNews() {
        newsRepository.getNews()
    }
    fun getComments(commentsId:String?) {
        commentsRepository.getComments(commentsId!!)
    }
    fun observeComments(): LiveData<Resource<CommentsData>> {
        return commentsObservable
    }
    fun addComment(newsId:String?,name:String?,email:String?,content:String?) {
        commentsActionRepository.comment(newsId!!, name!!, email!!, content!!)
    }
    fun getCategories(){
        categoriesRepository.getCategories()
    }
}