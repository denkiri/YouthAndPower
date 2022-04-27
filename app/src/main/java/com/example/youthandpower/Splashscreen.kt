package com.example.youthandpower
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
class Splashscreen : AppCompatActivity() {
    private val time:Long=4000 // 3 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        Handler(Looper.getMainLooper()).postDelayed({

            init()
        }, time)
    }
    fun  init() {
            startActivity(Intent(this@Splashscreen, HomeActivity::class.java))
            finish()


    }
}