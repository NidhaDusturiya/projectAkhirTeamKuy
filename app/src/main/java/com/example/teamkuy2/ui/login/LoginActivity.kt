package com.example.teamkuy2.ui.login
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teamkuy2.MainActivity
import com.example.teamkuy2.databinding.FragmentLoginBinding
import com.example.teamkuy2.ui.register.RegisterActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    lateinit var binding: FragmentLoginBinding
    lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        auth = FirebaseAuth.getInstance()

        binding.daftarAkun.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        binding.btnMasuk.setOnClickListener{


            val email = binding.inputEmail.text.toString()
            val password = binding.inputPass.text.toString()

            if (email.isEmpty()){
            binding.inputEmail.error = "email harus diisi"
            binding.inputEmail.requestFocus()
            return@setOnClickListener
            }
            //validasi email tidak sesuai
            else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.inputEmail.error = "Email tidak valid"
            binding.inputEmail.requestFocus()
            return@setOnClickListener
             }
             //validasi password
            else if (password.isEmpty()){
            binding.inputPass.error = "password harus diisi"
            binding.inputPass.requestFocus()
            return@setOnClickListener
             }

            LoginFirebase(email, password)
        }
    }
            private fun LoginFirebase(email: String, password: String){
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this){
                        if (it.isSuccessful){
                            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, "email atau password salah", Toast.LENGTH_SHORT).show()
                    }
                }
            }

    }