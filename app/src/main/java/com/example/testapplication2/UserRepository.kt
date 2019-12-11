package com.example.testapplication2

import android.content.Context
import androidx.lifecycle.LiveData

class UserRepository {

    private var userDao: UserDao
    private var appDatabase: UsersAppDatabase
    private var allUsers: LiveData<List<User>>

    constructor(context: Context) {
        appDatabase = UsersAppDatabase.getUserAppDatabase(context)
        userDao = appDatabase.userDao()
        allUsers = userDao.getAllUsers()
    }

    fun insert(user: User) {
        InsertUserAsyncTask(userDao).execute(user)
    }

    fun delete(user: User) {
        DeleteUserAsyncTask(userDao).execute(user)
    }

    fun update(user: User) {
        UpdateUserAsyncTask(userDao).execute(user)
    }

    fun deleteAllUsers() {
        DeleteUserAsyncTask(userDao).execute(null)
    }

    fun getAllUsers(): LiveData<List<User>> {
        return allUsers
    }
}