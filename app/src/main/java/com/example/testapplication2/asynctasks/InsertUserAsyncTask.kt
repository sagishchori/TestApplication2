package com.example.testapplication2.asynctasks

import android.os.AsyncTask
import com.example.testapplication2.database.UserDao
import com.example.testapplication2.models.User

class InsertUserAsyncTask(var userDao: UserDao): AsyncTask<User, Void, Void>() {

    override fun doInBackground(vararg params: User?): Void? {
        val user: User? = params[0]
        if (user != null) {
            userDao.insertUser(user)
        }
        return null
    }
}