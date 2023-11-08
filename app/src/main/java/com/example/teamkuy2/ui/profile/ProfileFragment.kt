package com.example.teamkuy2.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.example.teamkuy2.databinding.FragmentProfileBinding
import com.example.teamkuy2.ui.login.LoginActivity
import com.example.teamkuy2.ui.model.ResponseDetailUser
import com.example.teamkuy2.ui.network.ApiClient
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment(){
    private var mbinding: FragmentProfileBinding? = null
    private lateinit var sharedPreferences: android.content.SharedPreferences
    private val binding get() = mbinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mbinding = FragmentProfileBinding.inflate(inflater, container,false)
        val root: View = binding.root
        val database = FirebaseDatabase.getInstance()
        val reference = database.getReference("users")
        val preferencesDataStore = com.example.teamkuy2.ui.SharedPreferences(requireContext())
        val dataid = preferencesDataStore.getValue2()

        if (dataid != null){
            val githubUsernameReference = reference.child(dataid).child("usernameGithub")
            githubUsernameReference.addListenerForSingleValueEvent(object  : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val githubUsername = snapshot.getValue(String::class.java)
                    if (githubUsername != null){
                        getUser(githubUsername)
                    }else{
                        Toast.makeText(activity, "Pengguna Tidak ditemukan", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(activity, "error database", Toast.LENGTH_SHORT).show()
                }
            })
        }
        sharedPreferences = requireActivity().getSharedPreferences("user_data", AppCompatActivity.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("is_logged_in", false)

        if (isLoggedIn){
            val username = sharedPreferences.getString("username", "")
            val email = sharedPreferences.getString("email", "")
            val firstChar = username?.take(1)
        }
        binding.btnLogout.setOnClickListener{
            val edit = sharedPreferences.edit()
            edit.putBoolean("is_logged_in", false)
            edit.apply()

            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        return root
    }
    private fun getUser(usergithub: String) {
        val client = ApiClient.githubService.getDetailUserGithub(usergithub)
        client.enqueue(object : Callback<ResponseDetailUser>{
            override fun onResponse(
                call: Call<ResponseDetailUser>,
                response: Response<ResponseDetailUser>
            ) {
                if(response.isSuccessful){
                    val Arraydata = response.body()
                    if(Arraydata != null){
                        binding.apply {
                            imageProfile.load(Arraydata.avatar_url){
                                transformations(CircleCropTransformation())
                            }
                            tvUnameGithub.text = Arraydata.login
                            tvBio.text = Arraydata.bio.toString()
                            txtFullname.text = Arraydata.name
                            txtCompany.text = Arraydata.company
                            txtLocation.text = Arraydata.location
                            totalFollowers.text = Arraydata.followers.toString()
                            totalFollowing.text = Arraydata.following.toString()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<ResponseDetailUser>, t: Throwable) {
                Toast.makeText(activity, "error client.enqueue", Toast.LENGTH_SHORT).show()
                t.printStackTrace()
            }
        })
    }
}