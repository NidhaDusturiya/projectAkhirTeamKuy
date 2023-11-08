package com.example.teamkuy2.ui.detaill

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import coil.transform.CircleCropTransformation
import com.example.teamkuy2.MainActivity
import com.example.teamkuy2.databinding.FragmentHomeDetailBinding
import com.example.teamkuy2.ui.home.Result
import com.example.teamkuy2.ui.model.ResponseDetailUser

class HomeDetailFragment : Fragment() {
    private lateinit var binding: FragmentHomeDetailBinding
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        binding = FragmentHomeDetailBinding.inflate(inflater, container, false)
        val username = requireArguments().getString("username")?: ""

        // Hide the BottomNavigationView
        (requireActivity() as? MainActivity)?.hideBottomNavigationView()
        //back to main
        binding.back.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }
        //hide action bar
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

//        val item = arguments?.getParcelable<ResponseGithub.item>("item")


        viewModel.resultDetailUser.observe(viewLifecycleOwner) {
            when (it){
                is Result.Success<*> -> {
                    val user = it.dataa as ResponseDetailUser
                    binding.imageDetail.load(user.avatar_url){
                        transformations(CircleCropTransformation())
                    }
                    binding.tvUsernameDetail.text = user.login
                    binding.tvUrlGithub.text = user.html_url
                    binding.totalFollowers.text = user.followers.toString()
                    binding.totalFollowing.text = user.following.toString()
                    binding.txtFullname.text = user.name
                    binding.txtLocation.text = user.location
                    binding.txtCompany.text = user.company

                }
                is Result.Error-> {
                    Toast.makeText(requireContext(), it.exception.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is Result.Loading -> {
                    binding.progressBar.isVisible = it.isLoading
                }
            }
        }
        viewModel.getDetailUser(username)
        return  binding.root



    }


}