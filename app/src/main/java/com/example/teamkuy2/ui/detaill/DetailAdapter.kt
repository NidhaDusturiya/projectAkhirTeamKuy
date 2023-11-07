package com.example.teamkuy2.ui.detaill


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailAdapter (fa: FragmentActivity,
                     private val fragment: MutableList<Fragment>
) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = fragment.size
    override fun createFragment(position: Int): Fragment = fragment[position]
}
//class DetailAdapter(private val fragment: Fragment, private val fragments: List<Fragment>) : FragmentStateAdapter(fragment) {
//    override fun getItemCount(): Int = fragments.size
//    override fun createFragment(position: Int): Fragment = fragments[position]
//}