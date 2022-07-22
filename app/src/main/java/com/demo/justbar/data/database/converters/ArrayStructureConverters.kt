package com.demo.justbar.data.database.converters

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import javax.inject.Inject

class ArrayStructureConverters @Inject constructor() {

    @TypeConverter
    fun stringToList(value: String): List<String> {
        return Gson().fromJson(value,  object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun listToString(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun stringToMap(value: String): Map<String, Int> {
        return Gson().fromJson(value,  object : TypeToken<Map<String, Int>>() {}.type)
    }

    @TypeConverter
    fun mapToString(value: Map<String, Int>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun stringToMapOfMaps(value: String): List<Map<String, Int>> {
        return Gson().fromJson(value,  object : TypeToken<List<Map<String, Int>>>() {}.type)
    }

    @TypeConverter
    fun mapOfMapsToString(value: List<Map<String, Int>>): String {
        return Gson().toJson(value)
    }
}
