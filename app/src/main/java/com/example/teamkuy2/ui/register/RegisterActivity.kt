package com.example.teamkuy2.ui.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.teamkuy2.R
import com.google.firebase.auth.FirebaseAuth
import  com.example.teamkuy2.databinding.FragmentRegisterBinding
import com.example.teamkuy2.ui.Users
import com.example.teamkuy2.ui.login.LoginActivity
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity(){
    lateinit var binding : FragmentRegisterBinding
    lateinit var auth : FirebaseAuth
    lateinit var database : FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = FragmentRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        binding.btndaftar.setOnClickListener {
            val username = binding.inputNamaPengguna.text.toString()
            val usernameGithub = binding.inputNamaPenggunaGithub.text.toString()
            val nim = binding.inputNim.text.toString()
            val email = binding.inputEmail.text.toString()
            val password = binding.inputPassword.text.toString()


            //validasi username github
            if (usernameGithub.isEmpty()){
            binding.inputNamaPenggunaGithub.error = "username github harus diisi"
            binding.inputNamaPenggunaGithub.requestFocus()
            return@setOnClickListener
            }
            //validasi username
            else if (username.isEmpty()){
                binding.inputNamaPengguna.error = "username harus diisi"
                binding.inputNamaPengguna.requestFocus()
                return@setOnClickListener
            }
            //validasi username
            else if (nim.isEmpty()){
                binding.inputNim.error = "nim harus diisi"
                binding.inputNim.requestFocus()
                return@setOnClickListener
            }

            //validasi email
            else if (email.isEmpty()){
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
                binding.inputPassword.error = "password harus diisi"
                binding.inputPassword.requestFocus()
                return@setOnClickListener
            }
            else if(password.length < 8){
                binding.inputPassword.error = "Password minimal 8 karakter"
                binding.inputPassword.requestFocus()
                return@setOnClickListener
            }else{
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful){
                        val databaseref = database.reference.child("users").child(auth.currentUser!!.uid)
                        val users : Users = Users(username,usernameGithub,nim,email,password, auth.currentUser!!.uid )

                        databaseref.setValue(users).addOnCompleteListener {
                            if(it.isSuccessful){
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                Toast.makeText(this, "Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                            }else{
                                Toast.makeText(this, "kesalahan", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    else{
                        Toast.makeText(this, "kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
            binding.backlogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}
