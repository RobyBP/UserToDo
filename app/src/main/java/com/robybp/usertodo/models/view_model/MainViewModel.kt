package com.robybp.usertodo.models.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.robybp.usertodo.models.data_models.Task
import com.robybp.usertodo.models.data_models.User
import com.robybp.usertodo.models.db.UserTasksDatabase
import com.robybp.usertodo.models.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val taskRepository : TaskRepository
    private lateinit var task : Task

    private lateinit var allTasks : LiveData<List<Task>>
    var allUsers : LiveData<List<User>>


    init {
        val taskDao = UserTasksDatabase.getDatabase(application)!!.userDao()
        taskRepository = TaskRepository(taskDao)
        allUsers = taskRepository.allUsers

    }


    fun saveUser(user: User) = viewModelScope.launch(Dispatchers.IO){
        taskRepository.saveUser(user)
    }

    fun saveTask(task: Task) = viewModelScope.launch(Dispatchers.IO){
        taskRepository.saveTask(task)
    }

    fun deleteTask(task : Task) = viewModelScope.launch(Dispatchers.IO){
        taskRepository.deleteTask(task)
    }

    fun updateTask(task : Task) = viewModelScope.launch(Dispatchers.IO){
        taskRepository.updateTask(task)
    }

    fun getAllTasksById() : LiveData<List<Task>>{

        allTasks = taskRepository.allTasks
        return allTasks
    }

    fun setUserId(id : Int){
        taskRepository.setId(id)

    }

    fun getUserId() : Int{
        return taskRepository.getId()
    }

    fun setTask(task : Task){
        this.task = task
    }


    fun getTask() : Task{
        return this.task
    }

}