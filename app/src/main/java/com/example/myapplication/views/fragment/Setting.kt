package com.example.myapplication.views.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentSettingBinding
import com.example.myapplication.databinding.FragmentUserProfileBinding


class Setting: Fragment()  {

    private lateinit var _binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? { _binding = FragmentSettingBinding.inflate(inflater,container, false)
        return _binding.root
    }

}