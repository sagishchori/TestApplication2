package com.example.testapplication2

import android.os.AsyncTask

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