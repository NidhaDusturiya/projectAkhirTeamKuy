package com.example.teamkuy2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teamkuy2.databinding.FragmentHomeBinding
import com.example.teamkuy2.ui.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = UserAdapter() // Inisialisasi adapter

        binding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.adapter = adapter

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = ApiClient.githubService.getUserGithub()
                launch(Dispatchers.Main) {
                    // Do something on the main thread if needed
                }
                adapter.setData(response)
            } catch (e: Exception) {
                activity?.runOnUiThread {
                    Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


