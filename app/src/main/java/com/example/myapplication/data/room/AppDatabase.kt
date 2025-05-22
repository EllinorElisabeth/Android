package com.example.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.model.ApiRMCharacter
import com.example.myapplication.data.model.CreateRMCharacter
import com.example.myapplication.data.model.FavoriteRMCharacterCrossRef

@Database(
    entities = [ApiRMCharacter::class, CreateRMCharacter::class, FavoriteRMCharacterCrossRef::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}