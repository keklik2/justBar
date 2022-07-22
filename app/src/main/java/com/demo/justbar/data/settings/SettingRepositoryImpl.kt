package com.demo.justbar.data.settings

import android.app.Application
import android.content.Context

class SettingRepositoryImpl(
    private val app: Application
) {

    companion object {
        private const val SP_NAME = "inside_settings"

        private const val KEY_VERSION = "key_version"
        private const val BASE_VERSION = 0
    }

    private val sp = app.getSharedPreferences(
        SP_NAME,
        Context.MODE_PRIVATE
    )

    init {
        sp.edit().commit()
    }

    fun setVersion(version: Int) {
        sp.edit().apply{
            putInt(
                KEY_VERSION,
                version
            )
        }.apply()
    }

    fun getVersion(): Int {
        return sp.getInt(
            KEY_VERSION,
            BASE_VERSION
        )
    }

}
