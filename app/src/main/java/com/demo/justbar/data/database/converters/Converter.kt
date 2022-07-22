package com.demo.justbar.data.database.converters

import com.demo.justbar.data.database.CocktailDbModel
import com.demo.justbar.domain.Cocktail

object Converter {

    fun getCocktailDbModel(cocktail: Cocktail, isFavourite: Boolean = false): CocktailDbModel =
        CocktailDbModel(
            0,
            cocktail.title,
            cocktail.glassType,
            cocktail.isIBA,
            cocktail.alcoholPer,
            cocktail.volume,
            cocktail.alcoholIngredients,
            cocktail.otherIngredients,
            cocktail.taste,
            cocktail.recipe,
            cocktail.commonCocktails,
            cocktail.alternativeIngredients,
            isFavourite
        )
}
