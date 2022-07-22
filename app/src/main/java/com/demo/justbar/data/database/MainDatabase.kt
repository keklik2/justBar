package com.demo.justbar.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demo.justbar.data.database.converters.ArrayStructureConverters
import com.demo.justbar.data.database.converters.EnumConverters

@Database(entities = [CocktailDbModel::class],version = 1, exportSchema = false)
@TypeConverters(EnumConverters::class, ArrayStructureConverters::class)
abstract class MainDatabase: RoomDatabase() {
    abstract fun cocktailsDao(): CocktailsDao
}
