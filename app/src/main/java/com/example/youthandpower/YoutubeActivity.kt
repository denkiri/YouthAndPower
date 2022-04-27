package com.example.youthandpower

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_youtube.*
class YoutubeActivity : YouTubeBaseActivity(){
    val api_key =  "AIzaSyDm5Qs_hrhV8PCEZVAgzExu00HseI2Hl7w"
    var newsID: String?=null
    var videoId:String?=null
    var title:String?=null
    var content:String?=null
    var image:String?=null
    var author:String?=null
    var date:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        newsID = intent.getStringExtra("newsId")
        videoId = intent.getStringExtra("videoId")!!.trim()
        title = intent.getStringExtra("title")
        content = intent.getStringExtra("content")
        image = intent.getStringExtra("image")
        author=intent.getStringExtra("author")
        date=intent.getStringExtra("date")
        back.setOnClickListener { finish() }
        val ytPlayer = findViewById<YouTubePlayerView>(R.id.ytPlayer)
        var htmlText = intent.getStringExtra("content")
        topic.text = intent.getStringExtra("title")
        authorName.text=getString(R.string.by)+ intent.getStringExtra("author")
        dates.text=intent.getStringExtra("date")
        desc.text = HtmlCompat.fromHtml(htmlText!!, 0)
        if (videoId != "") {
            ytPlayer.initialize(api_key, object : YouTubePlayer.OnInitializedListener {
                // Implement two methods by clicking on red error bulb
                // inside onInitializationSuccess method
                // add the video link or the
                // playlist link that you want to play
                // In here we also handle the play and pause functionality
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    player: YouTubePlayer?,
                    p2: Boolean
                ) {
                    player?.loadVideo(videoId)
                    player?.play()
                }

                // Inside onInitializationFailure
                // implement the failure functionality
                // Here we will show toast
                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Toast.makeText(this@YoutubeActivity, "Video player Failed", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }
        else{
            ytPlayer.visibility=View.INVISIBLE
            images.visibility=View.VISIBLE
            Glide.with(this).load(image).centerCrop().placeholder(R.drawable.pic)
                .error(R.drawable.appstore).into(images)
        }
        comment.setOnClickListener {
            val intent = Intent(this, CommentsActivity::class.java)
            intent.putExtra("newsId", newsID)
            startActivity(intent)
            //focusOnView()
        }
    }
    /*
    private fun focusOnView() {
        Handler().post({ scrollView.scrollTo(0, commentSection.getTop()) })
    }

     */
}