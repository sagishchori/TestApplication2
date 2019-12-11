package com.example.testapplication2.viewmodels

import android.app.Application
import android.view.View
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testapplication2.R
import com.example.testapplication2.models.User
import com.example.testapplication2.repositories.UserRepository

class EditUserActivityViewModel : AndroidViewModel, View.OnClickListener {

    private lateinit var userRepository: UserRepository
    private var user: MutableLiveData<User> = MutableLiveData()
    private var isAddUser: MutableLiveData<Boolean> = MutableLiveData()

    constructor(application: Application) : super(application) {
        userRepository =
            UserRepository(application)
    }

    fun setUserData(user: User) {
        this.user.value = user
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.fab -> {
                Toast.makeText(v.context, "On SAVE USER clicked", Toast.LENGTH_LONG).show()
                saveUserToDB()
            }
        }
    }

    private fun saveUserToDB() {
        if (isAddUser.value!!) {
            insert(user.value!!)
        } else {
            update(user.value!!)
        }
    }

    fun getUser(): MutableLiveData<User>? {
        return user
    }

    fun setIsAddUser(isAddUser: Boolean) {
        this.isAddUser.value = isAddUser
    }

    private fun insert(user: User) {
        userRepository.insert(user)
    }

    private fun update(user: User) {
        userRepository.update(user)
    }
}