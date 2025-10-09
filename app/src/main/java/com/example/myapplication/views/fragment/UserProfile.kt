package com.example.myapplication.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentUserProfileBinding
import com.example.myapplication.listener.UserClickListener
import com.example.myapplication.model.User
import com.example.myapplication.views.adapter.UserAdapter


class UserProfile: Fragment(), UserClickListener  {

    private lateinit var _binding: FragmentUserProfileBinding
    private lateinit var adapter: UserAdapter
    private val userList = arrayListOf(
        User("Vivek Sharma", "vivek@example.com"),
        User("Aditi Rao", "aditi@example.com"),
        User("Saho Rao", "aditi@example.com"),
        User("Sam Rao", "aditi@example.com"),
        User("Karan Rao", "aditi@example.com"),
        User("Jacky Rao", "aditi@example.com"),
        User("Rahul Mehta", "rahul@example.com")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { _binding = FragmentUserProfileBinding.inflate(inflater,container, false)
        setData()
        return _binding.root
    }

    private fun setData(){
        adapter = UserAdapter(userList, this)
        _binding.rec.layoutManager = LinearLayoutManager(requireContext())
        _binding.rec.adapter = adapter
    }

    override fun onUSerClick(user: User) {
        Toast.makeText(requireContext(), "${user.name}", Toast.LENGTH_LONG).show()
    }

}