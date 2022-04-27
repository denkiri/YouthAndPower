package com.example.youthandpower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.youthandpower.ui.main.CommentsFragment

class CommentsActivity : AppCompatActivity() {
   var  newsId:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comments_activity)
        newsId = intent.getStringExtra("newsId")
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CommentsFragment.newInstance())
                .commitNow()
        }
    }
}