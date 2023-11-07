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
import com.example.teamkuy2.R
import com.example.teamkuy2.databinding.FragmentHomeDetailBinding
import com.example.teamkuy2.ui.detaill.aboutFollow.FollowFragment
import com.example.teamkuy2.ui.home.Result
import com.example.teamkuy2.ui.model.ResponseDetailUser
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

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
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        viewModel.resultDetailUser.observe(viewLifecycleOwner) {
            when (it){
                is Result.Success<*> -> {
                    val user = it.dataa as ResponseDetailUser
                    binding.imageDetail.load(user.avatar_url){
                        transformations(CircleCropTransformation())
                    }
                    binding.tvUsernameDetail.text = user.followers.toString()
                    binding.tvLocationDetail.text = user.location

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

        val fragments = mutableListOf<Fragment>(
            FollowFragment.newInstance(FollowFragment.FOLLOWERS),
            FollowFragment.newInstance(FollowFragment.FOLLOWING)
        )
        val titleFragments = mutableListOf(
            getString(R.string.followers), getString(R.string.following),
        )
        val adapter = DetailAdapter(requireActivity(), fragments)
        binding.viewpager.adapter = adapter

        TabLayoutMediator(binding.tab, binding.viewpager) { tab, posisi ->
            tab.text = titleFragments[posisi]
        }.attach()

        binding.tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 0) {
                    viewModel.getFollowers(username)
                } else {
                    viewModel.getFollowing(username)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
        viewModel.getFollowers(username)
        return  binding.root



    }


}