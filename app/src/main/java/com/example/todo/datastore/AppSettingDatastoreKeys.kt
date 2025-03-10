package com.example.todo.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey

object AppSettingDatastoreKeys {
    val IS_NOTIFICATION_ENABLED = booleanPreferencesKey("is_notification_enabled")
}