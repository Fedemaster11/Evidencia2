package com.example.evidencia2_skillmatch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.evidencia2_skillmatch.data.User
import com.example.evidencia2_skillmatch.databinding.ItemUserBinding

class UserAdapter(private val users: List<User>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = users[position]
        holder.binding.tvNombre.text = "${user.firstName} ${user.lastName}"
        holder.binding.tvEdad.text = "Edad: ${user.age}"
        holder.binding.tvAprender.text = "Quiere aprender: ${user.skillsToLearn}"
        holder.binding.tvEnsenar.text = "Puede enseñar: ${user.skillsToTeach}"
    }

    override fun getItemCount(): Int = users.size
}
