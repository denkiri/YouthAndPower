package com.example.youthandpower.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.youthandpower.R
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_youtube.*
import kotlinx.android.synthetic.main.fragment_youtube.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [YoutubeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class YoutubeFragment: Fragment()  {

    val api_key =  "AIzaSyDm5Qs_hrhV8PCEZVAgzExu00HseI2Hl7w"

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
        return inflater.inflate(R.layout.fragment_youtube, container, false)
    }
    /*
   override fun onActivityCreated(savedInstanceState: Bundle?) {

       super.onActivityCreated(savedInstanceState)

       ytPlayer.initialize(api_key, object : YouTubePlayer.OnInitializedListener{
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
               player?.loadVideo("HzeK7g8cD0Y")
               player?.play()
           }

           // Inside onInitializationFailure
           // implement the failure functionality
           // Here we will show toast
           override fun onInitializationFailure(
               p0: YouTubePlayer.Provider?,
               p1: YouTubeInitializationResult?
           ) {

           }
       })



    }
        */
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment YoutubeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            YoutubeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}