package com.example.testapplication2

import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.testapplication2.EditUserActivity.Companion.ADD_USER
import com.example.testapplication2.EditUserActivity.Companion.EDIT_USER
import com.example.testapplication2.EditUserActivity.Companion.USER

class MainActivityViewModel: AndroidViewModel, View.OnClickListener {

    private var usersList: LiveData<List<User>>
    private var userRepository: UserRepository

    constructor(application: Application) : super(application) {
        userRepository = UserRepository(application)
        usersList = userRepository.getAllUsers()
    }

    fun getUsersList(): LiveData<List<User>> {
        return usersList
    }

    fun insert(user: User) {
        userRepository.insert(user)
    }

    fun update(user: User) {
        userRepository.update(user)
    }

    fun deleteUser(user: User) {
        userRepository.delete(user)
    }

    fun deleteAllUsers() {
        userRepository.deleteAllUsers()
    }

    // A function to catch all click events on this (MainActivity) activity
    override fun onClick(v: View?) {
        when(v?.id) {

            R.id.fab -> {
                // Start new activity for adding User
                Toast.makeText(v.context, "On ADD USER clicked", Toast.LENGTH_LONG).show()

                var intent = Intent(v.context, EditUserActivity::class.java)
                intent.putExtra(ADD_USER, true)

                v.context.startActivity(intent)
            }

            // Start new activity for updating User
            else -> {

            }
        }
    }

    fun onUserItemClicked(user: User, context: Context) {
        Toast.makeText(context, "On EDIT USER clicked", Toast.LENGTH_LONG).show()

        var intent = Intent(context, EditUserActivity::class.java)
        intent.putExtra(EDIT_USER, true)
        intent.putExtra(USER, user)

        context.startActivity(intent)
    }


}