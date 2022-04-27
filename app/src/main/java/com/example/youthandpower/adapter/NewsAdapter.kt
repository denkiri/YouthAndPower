package com.example.youthandpower.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youthandpower.R
import com.example.youthandpower.models.Category
import com.example.youthandpower.models.News
import com.example.youthandpower.utils.OnCategoryClick

class NewsAdapter  (private val type: Int, private  val context: Context, private  var modelList: List<News>?, private val recyclerListener: OnCategoryClick): RecyclerView.Adapter<NewsItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsItemViewHolder {
        var itemView: View? =null
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.article_item,parent,false)
        return NewsItemViewHolder(type,itemView!!,recyclerListener)
    }
    override fun onBindViewHolder(holder: NewsItemViewHolder, position: Int) {
        val model= modelList!![position]
        if (model.videoId!="") {
        holder.play.visibility=View.VISIBLE
        }
        else{
        holder.play.visibility=View.INVISIBLE
        }
        holder.title.text =model.title
        holder.time.text =model.date
        holder.category.text=model.name
        Glide.with(context).load(model.img).centerCrop().placeholder(R.drawable.pic)
            .error(R.drawable.appstore).into(holder.image)


    }
    override fun getItemCount(): Int {
        return  if (null!= modelList)modelList!!.size else 0
    }
    fun refresh(modelList: List<News>?){
        this.modelList =modelList
        notifyDataSetChanged()
    }
}