package com.demo.justbar

import android.app.Application
import androidx.room.Room
import com.demo.justbar.data.Bar
import com.demo.justbar.data.database.MainDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JustBarApp: Application() {
//    lateinit var ads: AdsInterface
    private lateinit var db: MainDatabase

    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            MainDatabase::class.java,
            "Bar"
        ).fallbackToDestructiveMigration().build()

        Bar.initialize(db, this)
    }
}
