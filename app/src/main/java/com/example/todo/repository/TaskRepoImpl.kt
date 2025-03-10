package com.example.todo.repository

import android.icu.util.Calendar
import com.example.todo.database.dao.TaskDAO
import com.example.todo.database.entity.TaskCollection
import com.example.todo.database.entity.TaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepoImpl(private val taskDAO: TaskDAO) : TaskRepo {
    override suspend fun getTaskCollection(): List<TaskCollection> = withContext(Dispatchers.IO) {
        taskDAO.getTaskCollections()
    }

    override suspend fun getTaskByCollectionId(collectionId: Int): List<TaskEntity> =
        withContext(Dispatchers.IO) {
            taskDAO.getTasks(collectionId)
        }

    override suspend fun addTaskCollection(title: String): TaskCollection? {
        val taskCollection =
            TaskCollection(title = title, updatedAt = Calendar.getInstance().timeInMillis)
        val id = taskDAO.insertTaskCollection(taskCollection)
        return if (id > 0) {
            taskCollection.copy(id = id)
        } else {
            null
        }
    }
}