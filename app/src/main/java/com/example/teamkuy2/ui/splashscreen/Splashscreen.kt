package com.example.teamkuy2

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.teamkuy2.ui.OnBoarding

class Splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_splashscreen)
        supportActionBar?.hide()

        val splash = findViewById<ImageView>(R.id.splashs)

        splash.alpha = 0f
        splash.animate().setDuration(1000).alpha(1f).withEndAction {
            val intent = Intent(this, OnBoarding::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

    }
}