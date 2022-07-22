package com.demo.justbar.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.demo.justbar.domain.GlassType
import com.demo.justbar.domain.TasteType

@Entity(tableName = "cocktails")
data class CocktailDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val glassType: GlassType,
    val isIBA: Boolean,
    val alcoholPer: Int,
    val volume: Int,
    val alcoholIngredients: Map<String, Int>,
    val otherIngredients: Map<String, Int>,
    val taste: TasteType,
    val recipe: String,
    val commonCocktails: List<String>,
    val alternativeIngredients: List<Map<String, Int>>,
    val isFavourite: Boolean
)
