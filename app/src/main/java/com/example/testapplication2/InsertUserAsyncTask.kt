package com.example.testapplication2

import android.os.AsyncTask

class InsertUserAsyncTask(var userDao: UserDao): AsyncTask<User, Void, Void>() {

    override fun doInBackground(vararg params: User?): Void? {
        val user: User? = params[0]
        if (user != null) {
            userDao.insertUser(user)
        }
        return null
    }
}