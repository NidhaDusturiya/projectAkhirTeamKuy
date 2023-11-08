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

        val sharedPreferences = getSharedPreferences("user_data", MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false)

        val splash = findViewById<ImageView>(R.id.splashs)

        splash.alpha = 0f
        splash.animate().setDuration(1000).alpha(1f).withEndAction {
            val redirectIntent = if (isLoggedIn) {
                // Pengguna telah login, arahkan ke MainActivity
                Intent(this, MainActivity::class.java)
            } else {
                // Pengguna belum login, arahkan ke LoginActivity
                Intent(this, OnBoarding::class.java)
            }
            startActivity(redirectIntent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }
    }
}

