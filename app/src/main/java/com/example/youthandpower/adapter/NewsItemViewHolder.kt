package com.example.youthandpower.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.youthandpower.R
import com.example.youthandpower.utils.OnCategoryClick
import java.lang.ref.WeakReference

class NewsItemViewHolder (type: Int, itemView: View, listener: OnCategoryClick): RecyclerView.ViewHolder(itemView), View.OnClickListener {
    override fun onClick(v: View?) {
        if ( v == card_view ) {
            listenerWeakReference.get()?.selected(adapterPosition)
        }


    }
    private val listenerWeakReference: WeakReference<OnCategoryClick> = WeakReference(listener)
    var itemVew: View
    var card_view: CardView
    var title:TextView
    var time:TextView
    var play:ImageView
    var image:ImageView
    var category:TextView
    init {
        this.itemVew=itemView
        card_view=itemView.findViewById(R.id.card_view)
        title=itemView.findViewById(R.id.title)
        time=itemView.findViewById(R.id.time)
        image=itemView.findViewById(R.id.image)
        play=itemView.findViewById(R.id.play)
        category=itemView.findViewById(R.id.category)
        if (type==0) {

            card_view.setOnClickListener(this)
        }
    }
}