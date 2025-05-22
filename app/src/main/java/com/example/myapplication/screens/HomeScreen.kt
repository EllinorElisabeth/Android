package com.example.myapplication.screens

import CharacterItem
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.ui.RMTheme.allroundPadding_24
import com.example.myapplication.ui.RMTheme.BackgroundWallColor
import com.example.myapplication.ui.RMTheme.TitleBackgroundModifier
import com.example.myapplication.ui.RMTheme.titleStyle

import com.example.myapplication.viewmodel.RMCharacterViewModel


@Composable
fun HomeScreen(rmCharacterViewModel: RMCharacterViewModel) {

    val characters by rmCharacterViewModel.apiCharacters.collectAsState()
    val apiCharactersFromFavorite by rmCharacterViewModel.apiCharacterFromFavorite.collectAsState()
    val message = rmCharacterViewModel.message.collectAsState()

    Column(
        modifier = BackgroundWallColor
    ) {
        val context = LocalContext.current

        if (!message.value.isNullOrEmpty()) {
            Toast.makeText(context, message.value, Toast.LENGTH_LONG).show()
        }
        Text(
            "Rick and Morty characters!",
            style = titleStyle,
            modifier = TitleBackgroundModifier.then(allroundPadding_24)
        )
        LazyColumn(modifier = allroundPadding_24) {
            items(characters) { character ->
                CharacterItem(
                    character = character,
                    characterInFavorite = apiCharactersFromFavorite.any { apiCharacter ->
                        apiCharacter.apiCharacterId == character.apiCharacterId
                    },
                    favoriteIcon = true,
                    deleteFavoriteIcon = false,
                    deleteCreatedCharacterIcon = false,
                    addToFavoriteBtn = {
                        rmCharacterViewModel.toggleApiFavorite(character)
                    }
                )
            }
        }
    }

}









