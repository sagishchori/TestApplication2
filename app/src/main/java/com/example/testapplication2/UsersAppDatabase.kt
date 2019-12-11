package com.example.testapplication2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = arrayOf(User::class))
abstract class UsersAppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UsersAppDatabase? = null

        fun getUserAppDatabase(context: Context): UsersAppDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, UsersAppDatabase::class.java, "user_database").build()
                INSTANCE = instance
                return instance
            }
        }

        fun destroyDB() {
            this.INSTANCE = null
        }
    }
}