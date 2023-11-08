package com.example.teamkuy2.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teamkuy2.databinding.ItemListUserBinding
import com.example.teamkuy2.ui.model.ResponseGithub
import java.util.*

class UserAdapter(
    private var Alldata:MutableList<ResponseGithub.item> = mutableListOf(),
    private val listener : (ResponseGithub.item) -> Unit):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>(), Filterable {

    private  var filteredData: MutableList<ResponseGithub.item> = Alldata.toMutableList()

    init {
        this.Alldata= Alldata.toMutableList()
        this.filteredData = Alldata.toMutableList()
    }

    fun setData(data: MutableList<ResponseGithub.item>){
        this.Alldata.clear()
        this.Alldata.addAll(data)
        this.filteredData = Alldata.toMutableList()
        notifyDataSetChanged()
    }
    class UserViewHolder(private val v: ItemListUserBinding) : RecyclerView.ViewHolder(v.root){
        fun bind(item: ResponseGithub.item){
            Glide.with(v.itemAvatar)
                .load(item.avatar_url)
                .circleCrop()
                .into(v.itemAvatar)
            v.itemName.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemListUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = filteredData[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{
            listener(item)
        }
    }
    override fun getItemCount(): Int  = filteredData.size

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResult = FilterResults()
                val charSearch = constraint.toString().toLowerCase(Locale.ROOT)

                if (charSearch.isEmpty()){
                    filteredData = Alldata.toMutableList()
                }else{
                    val resultList = mutableListOf<ResponseGithub.item>()
                    for (row in Alldata) {
                        if (row.login.toLowerCase(Locale.ROOT).contains(charSearch)) {
                            resultList.add(row)
                        }
                    }
                    filteredData = resultList
                }
                val results = FilterResults()
                results.values = filteredData
                return results
            }
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredData = results?.values as? MutableList<ResponseGithub.item> ?: Alldata
                notifyDataSetChanged()
            }
        }
    } }