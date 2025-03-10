package com.example.todo.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_collection")
data class TaskCollection(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val updatedAt: Long
)
