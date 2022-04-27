package com.example.youthandpower.ui.main

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youthandpower.CommentsActivity
import com.example.youthandpower.R
import com.example.youthandpower.adapter.CategoryItemAdapter
import com.example.youthandpower.adapter.CommentsAdapter
import com.example.youthandpower.adapter.NewsAdapter
import com.example.youthandpower.custom.Resource
import com.example.youthandpower.custom.Status
import com.example.youthandpower.models.*
import com.example.youthandpower.utils.OnCategoryClick
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_comments.*
import kotlinx.android.synthetic.main.fragment_comments.avi
import kotlinx.android.synthetic.main.fragment_comments.recyclerView
import kotlinx.android.synthetic.main.main_fragment.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER "

/**
 * A simple [Fragment] subclass.
 * Use the [CommentsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CommentsFragment  : Fragment() {
    private lateinit var viewModel: MainViewModel
    lateinit var commentsAdapter: CommentsAdapter
    private lateinit var comments:List<Comments>
  var  newsId:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comments, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CommentsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            CommentsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.getComments((activity as CommentsActivity).newsId)
        cancel.setOnClickListener { activity?.finish() }
        initView()
        setUpUi()


    }
    private fun setStatus(data: Resource<CommentsData>) {
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
    fun init() {
        viewModel.getComments(newsId)
    }
    private fun setUpComments(comments: ArrayList<Comments>) {
        this.comments = comments
        commentsAdapter.refresh(this.comments)
        Handler().postDelayed(Runnable {
        }, 1)
    }

    fun setUpUi() {
        viewModel.observeComments().observe(
            viewLifecycleOwner,
            {
                setStatus(it)
                if (it.status == Status.SUCCESS) {
                    if (it.data?.comments != null && !it.data.comments!!.isEmpty()) {
                        setUpComments(it.data.comments as ArrayList<Comments>)
                    } else {
                        setUpComments(ArrayList())
                    }
                }

            })

    }
    private fun initView(){
        comments = ArrayList()
        commentsAdapter = context?.let {
            CommentsAdapter(0, it, comments, object : OnCategoryClick {

                override fun selected(pos: Int) {

                }
                override fun onClickListener(position1: Int) {
                }
            })
        }!!
        // recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = commentsAdapter
        commentsAdapter.notifyDataSetChanged()
    }




}