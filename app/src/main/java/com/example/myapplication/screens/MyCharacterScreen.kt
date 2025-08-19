package com.example.myapplication.screens

import CharacterItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.myapplication.ui.RMTheme.allroundPadding_24
import com.example.myapplication.ui.RMTheme.BackgroundWallColor
import com.example.myapplication.ui.RMTheme.TitleBackgroundModifier
import com.example.myapplication.ui.RMTheme.allroundPadding_16
import com.example.myapplication.ui.RMTheme.bodyTextStyleLarge
import com.example.myapplication.ui.RMTheme.bodyTextStyleMedium
import com.example.myapplication.ui.RMTheme.titleStyle
import com.example.myapplication.ui.RMTheme.topAndBottomPadding_16
import com.example.myapplication.viewmodel.RMCharacterViewModel


@Composable
fun MyCharacterScreen(rmCharacterViewModel: RMCharacterViewModel) {

    val characters by rmCharacterViewModel.createdCharacters.collectAsState()
    val createdCharactersInFavorite by rmCharacterViewModel.createdCharactersFromFavorite.collectAsState()

    Column(
        modifier = BackgroundWallColor
    ) {
        if (characters.isEmpty()) {
            Text(
                "No characters created yet...",
                style = titleStyle,
                modifier = TitleBackgroundModifier.then(
                    allroundPadding_24
                )
            )
            Text("Create your own characters in Create!",
                modifier = allroundPadding_24,
                style = bodyTextStyleMedium
            )
        } else {
            Text(
                "Your created characters",
                style = titleStyle,
                modifier = TitleBackgroundModifier.then(
                    allroundPadding_24
                )
            )
        }
        LazyColumn(
            modifier = allroundPadding_24
        ) {
            items(characters) { character ->
                CharacterItem(
                    character = character,
                    characterInFavorite = createdCharactersInFavorite.any { createdCharacter ->
                        createdCharacter.createdCharacterId == character.createdCharacterId
                    },
                    favoriteIcon = true,
                    deleteFavoriteIcon = false,
                    deleteCreatedCharacterIcon = true,
                    addToFavoriteBtn = {
                        rmCharacterViewModel.toggleCreatedFavorite(character)
                    },
                    deleteCreatedCharacterBtn = {
                        rmCharacterViewModel.deleteCreatedCharacter(character)
                    }
                )
            }
        }
    }

}












