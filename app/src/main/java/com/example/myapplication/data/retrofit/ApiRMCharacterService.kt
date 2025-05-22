package com.example.myapplication.data.retrofit

import com.example.myapplication.data.model.ApiRMCharacterList
import retrofit2.Response
import retrofit2.http.GET

interface ApiRMCharacterService {

    @GET("character")
    suspend fun getAllCharacters(): Response<ApiRMCharacterList>

}