package com.demo.justbar.data

import android.app.Application
import android.util.Log
import com.demo.justbar.data.database.CocktailDbModel
import com.demo.justbar.data.database.CocktailsRepositoryImpl
import com.demo.justbar.data.database.MainDatabase
import com.demo.justbar.data.settings.SettingRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

object Bar {
    // Make getVersion() receive version from settings
    lateinit var settings: SettingRepositoryImpl

    var loadScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    var initialized = false
    private lateinit var db: MainDatabase

    var allCocktails: List<CocktailDbModel> = listOf()
    var ibaCocktails: List<CocktailDbModel> = listOf()

    fun initialize(db: MainDatabase, app: Application) {
        this.settings = SettingRepositoryImpl(app)
        this.db = db
        if (initialized) return

        Log.d(TAG, "Starting initialization...")
        Server.getDbVersion(
            onSuccessCallback = { ver ->
                if (getVersion() < ver) {
                    Server.getAllCocktails(
                        onSuccessCallback = { cocktails ->
                            loadScope.launch {
                                CocktailsRepositoryImpl(db).replaceCocktails(cocktails)

                                allCocktails = db.cocktailsDao().getAll()
                                ibaCocktails = db.cocktailsDao().getAllIba()
                            }
                        }
                    )
                    setVersion(ver)
                } else
                    loadScope.launch {
                        allCocktails = db.cocktailsDao().getAll()
                        ibaCocktails = db.cocktailsDao().getAllIba()
                    }
            }
        )
    }

    /**
     * DB Version observer
     */
    private fun getVersion(): Int = settings.getVersion()
    private fun setVersion(version: Int) { settings.setVersion(version) }

    private const val TAG = "barMainClass"
}
