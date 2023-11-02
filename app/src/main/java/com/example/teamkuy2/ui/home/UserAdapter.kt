package com.example.teamkuy2.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.teamkuy2.databinding.ItemListUserBinding
import com.example.teamkuy2.ui.model.ResponseGithub

class UserAdapter(private val data:MutableList<ResponseGithub.ResponseGithubItem> = mutableListOf()):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(){

    fun setData(data:MutableList<ResponseGithub.ResponseGithubItem>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }
    class UserViewHolder(private val v: ItemListUserBinding) : RecyclerView.ViewHolder(v.root){
        fun bind(item: ResponseGithub.ResponseGithubItem){
            v.itemAvatar.load(item.avatarUrl){
                transformations(CircleCropTransformation())
            }
            v.itemName.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }
}