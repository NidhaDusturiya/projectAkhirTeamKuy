package com.example.teamkuy2.ui.detaill

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

        //back to main
        binding.back.setOnClickListener {
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        viewModel.resultDetailUser.observe(viewLifecycleOwner) {
            when (it){
                is Result.Success<*> -> {
                    val user = it.dataa as ResponseDetailUser
                    binding.imageDetail.load(user.avatar_url){
                        transformations(CircleCropTransformation())
                    }
                    binding.tvUsernameDetail.text = user.name

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