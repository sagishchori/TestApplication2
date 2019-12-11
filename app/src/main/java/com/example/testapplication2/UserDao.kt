package com.example.testapplication2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User): Long

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query ("DELETE FROM users")
    fun deleteAllUser()

    @Query ("SELECT * FROM users")
    fun getAllUsers(): LiveData<List<User>>

    @Query ("SELECT * FROM users WHERE id == :userId")
    fun getUser(userId: Long): User
}