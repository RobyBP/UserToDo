package com.robybp.usertodo.models.repository

import androidx.lifecycle.LiveData
import com.robybp.usertodo.models.dao.UserDao
import com.robybp.usertodo.models.data_models.Task
import com.robybp.usertodo.models.data_models.User

class TaskRepository(private val userDao: UserDao) {

    private var userId = 0

    lateinit var allTasks : LiveData<List<Task>>
    val allUsers : LiveData<List<User>> = userDao.getAllUsers()

    suspend fun saveUser(user : User){
        userDao.saveUser(user)
    }

    suspend fun saveTask(task : Task){
        userDao.saveTask(task)
    }

    suspend fun deleteTask(task : Task){
        userDao.deleteTask(task)
    }

    suspend fun updateTask(task : Task){
        userDao.updateTask(task)
    }


    fun setId(id : Int){
        this.userId = id
        allTasks = userDao.getAllUsersTasks(id)
    }

    fun getId() : Int{
        return this.userId
    }
}