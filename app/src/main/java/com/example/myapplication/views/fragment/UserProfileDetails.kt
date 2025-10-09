package com.example.myapplication.views.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentUserDetailsBinding


class UserProfileDetails: Fragment()  {
    private lateinit var _binding: FragmentUserDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { _binding = FragmentUserDetailsBinding.inflate(inflater,container, false)
        counter()
        return _binding.root
    }

    private fun counter(){
        _binding.submit.setOnClickListener {

            Log.d("Thread: ", "${Thread.currentThread().name} ")
            _binding.tvResult.text = "${_binding.tvResult.text.toString().toInt() + 1}"
        }
    }


}