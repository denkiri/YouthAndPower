package com.example.youthandpower.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youthandpower.R
import com.example.youthandpower.models.Comments
import com.example.youthandpower.utils.OnCategoryClick
class CommentsAdapter (private val type: Int, private  val context: Context, private  var modelList: List<Comments>?, private val recyclerListener: OnCategoryClick): RecyclerView.Adapter<CommentItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentItemViewHolder {
        var itemView: View? =null
        itemView = LayoutInflater.from(parent.context).inflate(R.layout.comments,parent,false)
        return CommentItemViewHolder(type,itemView!!,recyclerListener)
    }
    override fun onBindViewHolder(holder: CommentItemViewHolder, position: Int) {
        val model= modelList!![position]
        holder.name.text =model.name
        holder.date.text =model.date
        holder.comment.text=model.content
    }
    override fun getItemCount(): Int {
        return  if (null!= modelList)modelList!!.size else 0
    }
    fun refresh(modelList: List<Comments>?){
        this.modelList =modelList
        notifyDataSetChanged()
    }
}