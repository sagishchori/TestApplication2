package com.example.testapplication2.asynctasks

import android.os.AsyncTask
import com.example.testapplication2.models.User
import com.example.testapplication2.database.UserDao

class DeleteUserAsyncTask(var userDao: UserDao): AsyncTask<User, Void, Void>() {

    override fun doInBackground(vararg params: User?): Void? {
        val user: User? = params[0]
        if (user != null) {

            // Delete a specific User
            userDao.deleteUser(user)
        } else {

            // Delete the entire Users DB
            userDao.deleteAllUser()
        }
        return null
    }
}