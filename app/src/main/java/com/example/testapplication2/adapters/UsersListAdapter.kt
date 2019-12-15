package com.example.testapplication2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication2.models.User
import com.example.testapplication2.databinding.UserListItemBinding

class UsersListAdapter(val onUserItemClickListener: OnUserItemClickListener): RecyclerView.Adapter<UsersListAdapter.UserViewHolder>() {

    var usersList: List<User> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val userListItemBinding = UserListItemBinding.inflate(inflater)

        return UserViewHolder(
            userListItemBinding
        )
    }

    override fun getItemCount(): Int {
        return usersList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = usersList.get(position)

        // Set the User data to view list item
        holder.setUserData(user)

        // Set the click handler to view
        holder.setItemClickHandler(onUserItemClickListener)
    }

    fun setUsers(usersList: List<User>) {
        this.usersList = usersList
        notifyDataSetChanged()
    }

    class UserViewHolder(private var binding: UserListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setUserData(user: User) {
            binding.user = user
        }

        fun setItemClickHandler(onUserItemClickListener: OnUserItemClickListener) {
            binding.clickHandler = OnItemClickListener(onUserItemClickListener)
        }
    }

    class OnItemClickListener(private val onUserItemClickListener: OnUserItemClickListener) {

        fun onItemClicked(user: User) {
            onUserItemClickListener.onUserItemClicked(user)
        }
    }

    interface OnUserItemClickListener {

        fun onUserItemClicked(user: User)
    }
}