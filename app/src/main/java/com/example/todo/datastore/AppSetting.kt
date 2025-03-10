package com.example.todo.datastore

import kotlinx.coroutines.flow.Flow

interface AppSetting {
    val appSettingFlow: Flow<AppSettingData>

    suspend fun setIsNotificationEnabled(isEnabled: Boolean)

    suspend fun getIsNotificationEnabled(): Boolean
}