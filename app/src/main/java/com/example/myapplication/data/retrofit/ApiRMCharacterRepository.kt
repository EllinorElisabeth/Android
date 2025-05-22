package com.example.myapplication.data.retrofit

import android.util.Log
import com.example.myapplication.data.model.ApiRMCharacter
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiRMCharacterRepository {

    private val _httpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        ).build()

    private val _retrofit = Retrofit.Builder()
        .client(_httpClient)
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _apiRMCharacterService = _retrofit.create(ApiRMCharacterService::class.java)

    suspend fun getCharactersFromApi(): List<ApiRMCharacter> {
        return try {
            val response = _apiRMCharacterService.getAllCharacters()
            if (response.isSuccessful) {
                response.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("API FEIL", "FEIL UNDER HENTING AV API KARAKTERER ${e.message}")
            emptyList()
        }
    }


}