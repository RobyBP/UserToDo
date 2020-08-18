package com.robybp.usertodo.models.data_models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    val userId : Int,
    val userName : String,
    val password : String

)  {
}