package com.example.todo.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AppSettingImpl(private val context: Context) : AppSetting {
    private val Context.dataStoreAppSetting: DataStore<Preferences>
            by preferencesDataStore(name = "app_setting_pref")

    override val appSettingFlow: Flow<AppSettingData>
        get() = context.dataStoreAppSetting.data.map { pref ->
            AppSettingData(
                isNotificationEnabled = pref[AppSettingDatastoreKeys.IS_NOTIFICATION_ENABLED]
                    ?: false
            )
        }

    override suspend fun setIsNotificationEnabled(isEnabled: Boolean) =
        withContext(Dispatchers.IO) {
            context.dataStoreAppSetting.edit { pref ->
                pref[AppSettingDatastoreKeys.IS_NOTIFICATION_ENABLED] = isEnabled
            }
            Unit
        }

    override suspend fun getIsNotificationEnabled(): Boolean = withContext(Dispatchers.IO) {
       appSettingFlow.first().isNotificationEnabled
    }
}