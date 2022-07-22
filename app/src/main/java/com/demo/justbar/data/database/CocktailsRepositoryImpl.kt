package com.demo.justbar.data.database

import com.demo.justbar.data.database.converters.Converter
import com.demo.justbar.domain.Cocktail

class CocktailsRepositoryImpl(
    private val db: MainDatabase
) {

    suspend fun replaceCocktails(
        cocktails: List<Cocktail>
    ) {
        val favourites = db.cocktailsDao().getAllFavourites()
        db.cocktailsDao().dropData()

        for (cocktail in cocktails) {
            db.cocktailsDao().insert(
                Converter.getCocktailDbModel(
                cocktail,
                favourites.firstOrNull { it.title == cocktail.title } != null
            ))
        }
    }
}
