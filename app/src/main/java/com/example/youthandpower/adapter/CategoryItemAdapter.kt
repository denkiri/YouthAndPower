package com.example.youthandpower.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youthandpower.R
import com.example.youthandpower.models.Category
import com.example.youthandpower.utils.OnCategoryClick
class CategoryItemAdapter  (private val type: Int, private  val context: Context, private  var modelList: List<Category>?, private val recyclerListener: OnCategoryClick): RecyclerView.Adapter<CategoryItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        var itemView: View? =null
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.select_category_item,parent,false)
        return CategoryItemViewHolder(type,itemView!!,recyclerListener)
    }
    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val model= modelList!![position]
        holder.category_title.text =model.name

    }
    override fun getItemCount(): Int {
        return  if (null!= modelList)modelList!!.size else 0
    }
    fun refresh(modelList: List<Category>?){
        this.modelList =modelList
        notifyDataSetChanged()
    }
}