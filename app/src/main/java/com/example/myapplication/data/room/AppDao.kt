package com.example.myapplication.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.data.model.ApiRMCharacter
import com.example.myapplication.data.model.CreateRMCharacter
import com.example.myapplication.data.model.FavoriteRMCharacterCrossRef

@Dao
interface AppDao {

    // Hente
    @Query("select * from api_characters order by apiCharacterId asc")
    suspend fun getAllApiCharacters(): List<ApiRMCharacter>

    @Query("select * from created_characters order by createdCharacterId asc")
    suspend fun getAllCreatedCharacters(): List<CreateRMCharacter>

    @Transaction
    @Query(
        """
        select * from api_characters
        inner join favorite_characters
        on api_characters.apiCharacterId = favorite_characters.id
        where favorite_characters.id not null
        """
    )
    suspend fun getFavoriteApiCharacters(): List<ApiRMCharacter>

    @Transaction
    @Query(
        """
        select * from created_characters
        inner join favorite_characters
        on created_characters.createdCharacterId = favorite_characters.createdCharacterId
        where favorite_characters.createdCharacterId not null
    """
    )
    suspend fun getFavoriteCreatedCharacters(): List<CreateRMCharacter>


    // Legge til
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApiCharacters(character: ApiRMCharacter): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCreatedCharacters(character: CreateRMCharacter): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertApiCharacterToFavorite(character: FavoriteRMCharacterCrossRef): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCreatedCharacterToFavorite(character: FavoriteRMCharacterCrossRef): Long


    // Slette
    @Query("delete from created_characters where createdCharacterId = :createdCharacterId")
    suspend fun deleteCreatedCharacter(createdCharacterId: Int): Int

    @Query("delete from favorite_characters where favoriteCharacterId = :favoriteCharacterId")
    suspend fun deleteCharacterFromFavorite(favoriteCharacterId: Int): Int

    @Query("delete from favorite_characters where id = :id")
    suspend fun deleteApiCharacterFromFavorite(id: Int): Int

    @Query("delete from favorite_characters where createdCharacterId = :createdCharacterId")
    suspend fun deleteCreatedCharacterFromFavorite(createdCharacterId: Int): Int

}