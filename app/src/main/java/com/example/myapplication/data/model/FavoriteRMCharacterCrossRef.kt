package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite_characters",
)
data class FavoriteRMCharacterCrossRef(

    @PrimaryKey(autoGenerate = true)
    val favoriteCharacterId: Int = 0,
    val id: Int?,
    val createdCharacterId: Int?

)
