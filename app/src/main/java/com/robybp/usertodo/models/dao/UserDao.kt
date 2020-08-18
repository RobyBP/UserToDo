package com.robybp.usertodo.models.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.robybp.usertodo.models.data_models.Task
import com.robybp.usertodo.models.data_models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers() : LiveData<List<User>>

    @Query("SELECT * FROM tasks WHERE taskUserId = :userId ORDER BY taskPriority DESC")
    fun getAllUsersTasks(userId: Int) : LiveData<List<Task>>

    @Insert
    suspend fun saveUser(user : User)

    @Insert
    suspend fun saveTask(task : Task)

    @Delete
    suspend fun deleteTask(task : Task)

    @Update
    suspend fun updateTask(task : Task)

}