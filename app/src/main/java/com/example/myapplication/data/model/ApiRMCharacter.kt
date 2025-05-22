package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "api_characters")
data class ApiRMCharacter(

    @PrimaryKey
    @SerializedName("id") val apiCharacterId: Int,
    override val name: String,
    override val gender: String,
    override val species: String,
    override val status: String,
    override val image: String

) : RMCharacter

