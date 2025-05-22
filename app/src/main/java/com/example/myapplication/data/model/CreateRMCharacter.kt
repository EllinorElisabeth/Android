package com.example.myapplication.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "created_characters")
data class CreateRMCharacter(

    @PrimaryKey(autoGenerate = true)
    val createdCharacterId: Int = 0,
    override val name: String,
    override val gender: String,
    override val species: String,
    override val status: String,
    override val image: String? = null

) : RMCharacter
