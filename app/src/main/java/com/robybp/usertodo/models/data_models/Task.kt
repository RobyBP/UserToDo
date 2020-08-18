package com.robybp.usertodo.models.data_models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val taskUserId : Int,
    val taskName : String,
    val taskDescription : String,
    var taskPriority : Int

) {
}