package com.example.myapplication.views.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.views.fragment.Setting
import com.example.myapplication.views.fragment.UserProfile
import com.example.myapplication.views.fragment.UserProfileDetails

class ViewPagerAdapter(activity: AppCompatActivity):
    FragmentStateAdapter(
    activity
) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> UserProfile()
                1 -> UserProfileDetails()
            else -> Setting()
        }
    }
}