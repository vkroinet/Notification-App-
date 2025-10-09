package com.example.myapplication.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemUserBinding
import com.example.myapplication.listener.UserClickListener
import com.example.myapplication.model.User

class UserAdapter(private val users: ArrayList<User>,
    private val userListener: UserClickListener) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    //class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    inner class UserViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    userListener.onUSerClick(users[position])
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.binding.nameText.text = user.name
        holder.binding.emailText.text = user.email


    }

    override fun getItemCount(): Int = users.size
}