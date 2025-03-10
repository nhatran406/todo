package com.example.todo.repository

import com.example.todo.database.entity.TaskCollection
import com.example.todo.database.entity.TaskEntity

interface TaskRepo {
    suspend fun getTaskCollection(): List<TaskCollection>
    suspend fun getTaskByCollectionId(collectionId: Int): List<TaskEntity>
    suspend fun addTaskCollection(title: String): TaskCollection?
}