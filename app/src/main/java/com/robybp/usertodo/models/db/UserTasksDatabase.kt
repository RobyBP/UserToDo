package com.robybp.usertodo.models.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.robybp.usertodo.models.dao.UserDao
import com.robybp.usertodo.models.data_models.Task
import com.robybp.usertodo.models.data_models.User

@Database(entities = [User::class, Task::class], version = 1, exportSchema = false)
abstract class UserTasksDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {

        @Volatile
        private var INSTANCE : UserTasksDatabase? = null

        fun getDatabase(context: Context) : UserTasksDatabase? {
            val tempInstance = INSTANCE
            if (INSTANCE != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserTasksDatabase::class.java,
                    "user_task_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}