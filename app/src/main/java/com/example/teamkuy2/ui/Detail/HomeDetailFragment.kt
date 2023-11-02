package com.example.teamkuy2.ui.Detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.teamkuy2.R
import com.example.teamkuy2.databinding.FragmentHomeDetailBinding

class HomeDetailFragment : Fragment() {
    private lateinit var binding: FragmentHomeDetailBinding
    private val viewModel by viewModels<DetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeDetailBinding.inflate(inflater, container, false)

        val username = requireArguments().getString("username")?: ""
        viewModel.resultDetailUser.observe(viewLifecycleOwner){

        }
    }

}