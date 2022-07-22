package com.demo.justbar.data.database.converters

import androidx.room.TypeConverter
import com.demo.justbar.domain.GlassType
import com.demo.justbar.domain.TasteType
import javax.inject.Inject

class EnumConverters @Inject constructor() {

    @TypeConverter
    fun toGlassType(value: String) = enumValueOf<GlassType>(value)

    @TypeConverter
    fun fromGlassType(value: GlassType) = value.title

    @TypeConverter
    fun toTasteType(value: String) = enumValueOf<TasteType>(value)

    @TypeConverter
    fun fromTasteType(value: TasteType) = value.title

}
