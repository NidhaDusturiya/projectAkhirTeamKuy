package com.example.teamkuy2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamkuy2.R
import com.example.teamkuy2.databinding.FragmentHomeBinding
import com.example.teamkuy2.ui.detaill.HomeDetailFragment
import com.example.teamkuy2.ui.model.ResponseGithub


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: UserAdapter
    private lateinit var listUser: MutableList<ResponseGithub.ResponseGithubItem>

    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        adapter = UserAdapter(mutableListOf()){
            user ->  val detailFragment = HomeDetailFragment()
            val bundle = Bundle()
            bundle.putString("username", user.login)
            detailFragment.arguments = bundle

            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.NavHostFragment, detailFragment)
            transaction.commit()


        }
        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.adapter = adapter

        viewModel.resultUser.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success<*> -> {
                    listUser = result.dataa as MutableList<ResponseGithub.ResponseGithubItem>
                    adapter.setData(listUser)
                    setupSearchView()
                }
                is Result.Error -> {
                    Toast.makeText(requireContext(), result.exception.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is Result.Loading -> {
                    binding.progressBar.isVisible = result.isLoading
                }
            }
        }

        viewModel.getUser()

        return binding.root

    }
    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }


}

