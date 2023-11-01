package com.example.teamkuy2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.example.teamapp.ui.OnBoarding
import com.example.teamkuy2.ui.login.LoginActivity
import com.example.teamkuy2.ui.register.RegisterActivity

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