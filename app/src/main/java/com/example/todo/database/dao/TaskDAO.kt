package com.example.todo.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.todo.database.entity.TaskCollection
import com.example.todo.database.entity.TaskEntity

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskCollection(taskCollection: TaskCollection): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Int

    @Query("SELECT * FROM task_collection")
    suspend fun getTaskCollections(): List<TaskCollection>

    @Query("SELECT * FROM task WHERE collection_id = :collectionId")
    suspend fun getTasks(collectionId: Int): List<TaskEntity>

    @Query("UPDATE task SET is_completed = :isCompleted WHERE id = :taskId")
    suspend fun updateTaskCompletion(taskId: Int, isCompleted:Boolean)

    @Query("UPDATE task SET is_favorite = :isFavorite WHERE id = :taskId")
    suspend fun updateTaskFavorite(taskId: Int, isFavorite:Boolean)

    @Query("DELETE FROM task WHERE id = :taskId")
    suspend fun deleteTask(taskId: Int)

    @Query("DELETE FROM task_collection WHERE id = :collectionId")
    suspend fun deleteTaskCollection(collectionId: Int)

    @Update
    suspend fun updateTaskCollection(taskCollection: TaskCollection)

    @Update
    suspend fun updateTask(task: TaskEntity)
}