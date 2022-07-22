package com.demo.justbar.data.database

import androidx.room.*

@Dao
interface CocktailsDao {
    @Query("SELECT * FROM cocktails ORDER BY title")
    suspend fun getAll(): List<CocktailDbModel>

    @Query("SELECT * FROM cocktails WHERE isIBA == :isIba ORDER BY title")
    suspend fun getAllIba(isIba: Boolean = true): List<CocktailDbModel>

    @Query("SELECT * FROM cocktails WHERE isFavourite == :isFav ORDER BY title")
    suspend fun getAllFavourites(isFav: Boolean = true): List<CocktailDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cocktail: CocktailDbModel)

    @Delete
    suspend fun delete(cocktail: CocktailDbModel)

    @Query("DELETE FROM cocktails")
    suspend fun dropData()
}
