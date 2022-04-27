package com.example.youthandpower.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youthandpower.R
import com.example.youthandpower.utils.OnCategoryClick
import java.lang.ref.WeakReference

class CommentItemViewHolder (type: Int, itemView: View, listener: OnCategoryClick): RecyclerView.ViewHolder(itemView), View.OnClickListener {
    override fun onClick(v: View?) {
    }
    private val listenerWeakReference: WeakReference<OnCategoryClick> = WeakReference(listener)
    var itemVew: View
    var name: TextView
    var comment:TextView
    var date:TextView
    init {
        this.itemVew=itemView
        name=itemView.findViewById(R.id.name)
        comment=itemView.findViewById(R.id.comment)
        date=itemView.findViewById(R.id.date)

        if (type==0) {

        }
    }
}