package com.example.myapplication.data.room

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.myapplication.data.model.ApiRMCharacter
import com.example.myapplication.data.model.CreateRMCharacter
import com.example.myapplication.data.model.FavoriteRMCharacterCrossRef


object AppDbRepository {

    private lateinit var _appDatabase: AppDatabase
    private val _appDao by lazy { _appDatabase.appDao() }

    fun initializeDatabase(context: Context) {
        _appDatabase = Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "Database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    // Hente
    suspend fun getApiCharacters(): List<ApiRMCharacter> {
        try {
            return _appDao.getAllApiCharacters()
        } catch (e: Exception) {
            Log.e("DATABASE FEIL", "KUNNE IKKE HENTE: ${e.message}")
            return emptyList()
        }
    }

    suspend fun getCreatedCharacters(): List<CreateRMCharacter> {
        try {
            return _appDao.getAllCreatedCharacters()
        } catch (e: Exception) {
            Log.e("DATABASE FEIL", "KUNNE IKKE HENTE BRUKERGENERERTE KARAKTERER: ${e.message}")
            return emptyList()
        }
    }

    suspend fun getApiCharactersInFavorite(): List<ApiRMCharacter> {
        try {
            return _appDao.getFavoriteApiCharacters()
        } catch (e: Exception) {
            Log.e("DATABASE FEIL", "KUNNE IKKE HENTE API KARAKTERER I FAVORITTER: ${e.message}")
            return emptyList()
        }
    }

    suspend fun getCreatedCharactersInFavorites(): List<CreateRMCharacter> {
        try {
            return _appDao.getFavoriteCreatedCharacters()
        } catch (e: Exception) {
            Log.e(
                "DATABASE FEIL",
                "KUNNE IKKE HENTE BRUKERGENERERTE KARAKTERER I FAVORITTER: ${e.message}"
            )
            return emptyList()
        }
    }


    // Legger til
    suspend fun insertApiCharacters(characters: ApiRMCharacter): Long {
        try {
            return _appDao.insertApiCharacters(characters)
        } catch (e: Exception) {
            Log.e("DATABASE FEIL", "FIKK IKKE LAGT TIL API KARAKTERER I DATABASEN: ${e.message}")
            return -1L
        }
    }

    suspend fun insertCreatedCharacter(character: CreateRMCharacter): Long {
        try {
            return _appDao.insertCreatedCharacters(character)
        } catch (e: Exception) {
            Log.e(
                "DATABASE FEIL",
                "FIKK IKKE LAGT TIL BRUKERGENERERTE KARAKTERER I DATABASEN: ${e.message}"
            )
            return -1L
        }
    }

    suspend fun insertApiCharacterToFavorite(character: FavoriteRMCharacterCrossRef): Long {
        try {
            return _appDao.insertApiCharacterToFavorite(character)
        } catch (e: Exception) {
            Log.e("DATABASE FEIL", "FIKK IKKE LAGT TIL API KARAKTERER I FAVORITTER: ${e.message}")
            return -1L
        }
    }

    suspend fun insertCreatedCharacterToFavorite(character: FavoriteRMCharacterCrossRef): Long {
        try {
            return _appDao.insertCreatedCharacterToFavorite(character)
        } catch (e: Exception) {
            Log.e(
                "DATABASE FEIL",
                "FIKK IKKE LAGT TIL BRUKERGENERERTE KARAKTERER I FAVORITTER: ${e.message}"
            )
            return -1L
        }
    }

    // Slette
    suspend fun deleteCreatedCharacter(createdCharacterId: Int): Int {
        try {
            return _appDao.deleteCreatedCharacter(createdCharacterId)
        } catch (e: Exception) {
            Log.e(
                "DATABASE FEIL",
                "FIKK IKKE SLETTET BRUKERGENERERTE KARAKTERER FRA DATABASEN: ${e.message}"
            )
            return 0
        }
    }


    suspend fun deleteApiCharacterFromFavorite(id: Int): Int {
        try {
            return _appDao.deleteApiCharacterFromFavorite(id)
        } catch (e: Exception) {
            Log.e("DATABASE FEIL", "FIKK IKKE SLETTET API KARAKTERER FRA FAVORITTER: ${e.message}")
            return 0
        }
    }

    suspend fun deleteCreatedCharacterFromFavorite(createdCharacterId: Int): Int {
        try {
            return _appDao.deleteCreatedCharacterFromFavorite(createdCharacterId)
        } catch (e: Exception) {
            Log.e(
                "DATABASE FEIL",
                "FIKK IKKE SLETTET BRUKERGENERERTE KARAKTERER FRA FAVORITTER: ${e.message}"
            )
            return 0
        }
    }

}


