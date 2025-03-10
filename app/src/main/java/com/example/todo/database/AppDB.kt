package com.example.todo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.database.dao.TaskDAO
import com.example.todo.database.entity.TaskCollection
import com.example.todo.database.entity.TaskEntity

private const val DATABASE_NAME = "app_db"
private const val DATABASE_VERSION = 1

@Database(
    entities = [TaskEntity::class, TaskCollection::class],
    version = DATABASE_VERSION
)


abstract class AppDB : RoomDatabase() {
    abstract fun taskDAO(): TaskDAO

    companion object {
        private var instance: AppDB? = null
        operator fun invoke(context: Context): AppDB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDB::class.java,
            DATABASE_NAME
        ).build()
    }
}