package com.example.youthandpower.ui.main

import android.content.Intent
import android.graphics.Color
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.HorizontalScrollView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.youthandpower.adapter.CategoryItemAdapter
import com.example.youthandpower.adapter.MySliderImageAdapter
import com.example.youthandpower.custom.Resource
import com.example.youthandpower.custom.Status
import com.example.youthandpower.models.Category
import com.example.youthandpower.models.CategoryData
import com.example.youthandpower.utils.OnCategoryClick
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.main_fragment.*
import androidx.recyclerview.widget.RecyclerView
import com.example.youthandpower.HomeActivity
import com.example.youthandpower.R
import com.example.youthandpower.YoutubeActivity
import com.example.youthandpower.adapter.NewsAdapter
import com.example.youthandpower.models.News
import com.example.youthandpower.models.NewsData
import com.google.android.material.snackbar.Snackbar


class HomeFragment : Fragment() {
    lateinit var categoryAdapter: CategoryItemAdapter
    lateinit var newsAdapter:NewsAdapter
    private lateinit var news:List<News>
    private lateinit var categories: List<Category>
    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(com.example.youthandpower.R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        initView()
        initViewB()
        setUpUi()
        init()

        val imageSlider = imageSlider
        val imageList: ArrayList<String> = ArrayList()
        imageList.add("https://youthandpower.denkiri.com/sliders/slider1.jpg")
        imageList.add("https://youthandpower.denkiri.com/sliders/slider2.jpg")
        imageList.add("https://youthandpower.denkiri.com/sliders/slider3.jpg")
        imageList.add("https://youthandpower.denkiri.com/sliders/slider4.jpg")
        setImageInSlider(imageList, imageSlider)
        itemsswipetorefresh.setProgressBackgroundColorSchemeColor(ContextCompat.getColor(requireContext(), R.color.transparent))
        itemsswipetorefresh.setColorSchemeColors(Color.RED)
        itemsswipetorefresh.setOnRefreshListener {
            init()
            itemsswipetorefresh.isRefreshing = false
        }

    }

    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = MySliderImageAdapter()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = true
        imageSlider.startAutoCycle()
    }
    private fun setStatus(data: Resource<CategoryData>) {
        val status: Status = data.status

        if (status == Status.LOADING) {
            avi.visibility = View.VISIBLE
          //  activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else if (status == Status.ERROR || status == Status.SUCCESS) {
            avi.visibility = View.GONE
          //  activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }

        if (status == Status.ERROR) {
            if (data.message != null) {
            init()
                view?.let { Snackbar.make(it,"Error Loading Data", Snackbar.LENGTH_LONG).show() }
            }

        }
    }
    private fun setStatusB(data: Resource<NewsData>) {
        val status: Status = data.status

        if (status == Status.LOADING) {
            avi.visibility = View.VISIBLE
           // activity?.window?.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else if (status == Status.ERROR || status == Status.SUCCESS) {
            avi.visibility = View.GONE
          //  activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }

        if (status == Status.ERROR) {
            if (data.message != null) {
                init()
                view?.let { Snackbar.make(it,"Error Loading Data", Snackbar.LENGTH_LONG).show() }
            }

        }
    }
    private fun setUpCategories(categories: ArrayList<Category>) {
        this.categories = categories
        categoryAdapter.refresh(this.categories)
        Handler().postDelayed(Runnable {
        }, 1)
    }
    private fun setUpNews(news: ArrayList<News>) {
        this.news = news
        newsAdapter.refresh(this.news)
        Handler().postDelayed(Runnable {
        }, 1)
    }

    fun init() {
        viewModel.getCategories()
        viewModel.getNews()

    }
    fun setUpUi() {

        viewModel.observeCategories().observe(
            viewLifecycleOwner,
            {
                setStatus(it)
                if (it.status == Status.SUCCESS) {
                    if (it.data?.categories != null && !it.data.categories!!.isEmpty()) {
                        setUpCategories(it.data.categories as ArrayList<Category>)
                    } else {
                        setUpCategories(ArrayList())
                    }
                }

            })
        viewModel.observeNews().observe(
            viewLifecycleOwner,
            {
                setStatusB(it)
                if (it.status == Status.SUCCESS) {
                    if (it.data?.news != null && !it.data.news!!.isEmpty()) {
                        setUpNews(it.data.news as ArrayList<News>)
                    } else {
                        setUpNews(ArrayList())
                    }
                }

            })
    }
    private fun initView(){
        categories = ArrayList()
        categoryAdapter = context?.let {
            CategoryItemAdapter(0, it, categories, object : OnCategoryClick {

                override fun selected(pos: Int) {

                 }
                override fun onClickListener(position1: Int) {
                }
            })
        }!!
       // recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = categoryAdapter
        categoryAdapter.notifyDataSetChanged()
    }
    private fun initViewB(){
        news = ArrayList()
        newsAdapter = context?.let {
            NewsAdapter(0, it, news, object : OnCategoryClick {

                override fun selected(pos: Int) {
                    val intent = Intent(activity, YoutubeActivity::class.java)
                    intent.putExtra("newsId", news[pos].newsId.toString())
                    intent.putExtra("videoId",news[pos].videoId)
                    intent.putExtra("title",news[pos].title)
                    intent.putExtra("content",news[pos].content)
                    intent.putExtra("image",news[pos].img)
                    intent.putExtra("author",news[pos].author)
                    intent.putExtra("date",news[pos].date)
                    startActivity(intent)


                }
                override fun onClickListener(position1: Int) {
                }
            })
        }!!
         recyclerViewB.layoutManager = LinearLayoutManager(this.context)
          recyclerViewB.adapter = newsAdapter
        newsAdapter.notifyDataSetChanged()
    }
    }

