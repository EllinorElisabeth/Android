package com.example.myapplication.screens

import CharacterItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.RMTheme.allroundPadding_24
import com.example.myapplication.ui.RMTheme.BackgroundWallColor
import com.example.myapplication.ui.RMTheme.bodyTextStyleLarge
import com.example.myapplication.ui.RMTheme.TitleBackgroundModifier
import com.example.myapplication.ui.RMTheme.bodyTextStyleMedium
import com.example.myapplication.ui.RMTheme.bottomPadding_8
import com.example.myapplication.ui.RMTheme.titleStyle
import com.example.myapplication.viewmodel.RMCharacterViewModel


@Composable
fun FavoriteScreen(rmCharacterViewModel: RMCharacterViewModel) {

    val apiCharacterFavorite by rmCharacterViewModel.apiCharacterFromFavorite.collectAsState()
    val createdCharacterFavorite by rmCharacterViewModel.createdCharactersFromFavorite.collectAsState()

    LaunchedEffect(Unit) {
        rmCharacterViewModel.getApiCharactersFromFavorite()
        rmCharacterViewModel.getCreatedCharactersFromFavorite()
    }

    Column(
        modifier = BackgroundWallColor
    ) {
        if (apiCharacterFavorite.isEmpty() && createdCharacterFavorite.isEmpty()) {
            Text("No favorite characters...",
                style = titleStyle,
                modifier = TitleBackgroundModifier.then(allroundPadding_24))
        } else {
            Text(
                "Favorite characters",
                style = titleStyle,
                modifier = TitleBackgroundModifier.then(allroundPadding_24)
            )
        }
        LazyColumn(
            modifier = allroundPadding_24
        ) {
            item {
                if (apiCharacterFavorite.isEmpty()) {
                    Text("Your favorite Rick and Morty characters:\n" +
                            "None... But you can add them from Home!",
                        style = bodyTextStyleMedium)
                } else {
                    Text(
                        "Rick & Morty characters",
                        style = bodyTextStyleLarge,
                        modifier = bottomPadding_8
                    )
                }
            }
            items(apiCharacterFavorite) { character ->
                CharacterItem(
                    character,
                    characterInFavorite = true,
                    favoriteIcon = false,
                    deleteFavoriteIcon = true,
                    deleteCreatedCharacterIcon = false,
                    deleteCharacterFromFavoriteBtn = {
                        rmCharacterViewModel.deleteCharacterFromFavorite(character)
                    }
                )
            }

            item { Spacer(modifier = Modifier.padding(16.dp)) }

            item {
                if (createdCharacterFavorite.isEmpty()) {
                    Text("Your favorite created characters:\n" +
                            "None... But you can create and add them!",
                        style = bodyTextStyleMedium)
                } else {
                    Text(
                        "Your created characters",
                        style = bodyTextStyleLarge,
                        modifier = bottomPadding_8
                    )
                }
            }
            items(createdCharacterFavorite) { character ->
                CharacterItem(
                    character,
                    characterInFavorite = true,
                    favoriteIcon = false,
                    deleteFavoriteIcon = true,
                    deleteCreatedCharacterIcon = false,
                    deleteCharacterFromFavoriteBtn = {
                        rmCharacterViewModel.deleteCharacterFromFavorite(character)
                    }
                )
            }
        }
    }

}






