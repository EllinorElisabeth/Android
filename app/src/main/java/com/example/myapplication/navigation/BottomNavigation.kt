package com.example.myapplication.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.screens.CreateScreen
import com.example.myapplication.screens.FavoriteScreen
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.MyCharacterScreen
import com.example.myapplication.ui.RMTheme.GreenGlassHex
import com.example.myapplication.viewmodel.RMCharacterViewModel
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
object MyCharacter

@Serializable
object CreateCharacter

@Serializable
object Favorite


@Composable
fun BottomNavigation(rmCharacterViewModel: RMCharacterViewModel) {

    val navController = rememberNavController()
    var selectedValue by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.Black
            ) {
                NavigationBarItem(
                    selectedValue == 0,
                    onClick = {
                        selectedValue = 0
                        navController.navigate(Home)
                    },
                    icon = {
                        Icon(
                            if (selectedValue == 0) Icons.Filled.Home else Icons.Outlined.Home,
                            "Home"
                        )
                    },
                    label = {
                        Text(
                            "Home"
                        )
                    },
                    colors = defaultNavigationColors()
                )

                NavigationBarItem(
                    selectedValue == 1,
                    onClick = {
                        selectedValue = 1
                        navController.navigate(MyCharacter)
                    },
                    icon = {
                        Icon(
                            painter = if (selectedValue == 1) {
                                painterResource(R.drawable.filled_createdcharacters)
                            } else {
                                painterResource(R.drawable.outline_createdcharacters)
                            },
                            "Created Characters"
                        )
                    },
                    label = {
                        Text(
                            "Character"
                        )
                    },
                    colors = defaultNavigationColors()
                )

                NavigationBarItem(
                    selectedValue == 2,
                    onClick = {
                        selectedValue = 2
                        navController.navigate(CreateCharacter)
                    },
                    icon = {
                        Icon(
                            painter = if (selectedValue == 2) {
                                painterResource(R.drawable.filled_create)
                            } else {
                                painterResource(R.drawable.outline_create)
                            },
                            "Create character"
                        )
                    },
                    label = {
                        Text(
                            "Create"
                        )
                    },
                    colors = defaultNavigationColors()
                )

                NavigationBarItem(
                    selectedValue == 3,
                    onClick = {
                        selectedValue = 3
                        navController.navigate(Favorite)
                    },
                    icon = {
                        Icon(
                            if (selectedValue == 3) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                            "Favorite"
                        )
                    },
                    label = {
                        Text(
                            "Favorite"
                        )
                    },
                    colors = defaultNavigationColors()
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(
                top = 0.dp,
                bottom = innerPadding.calculateBottomPadding()
            )
        ) {
            NavHost(
                navController = navController,
                startDestination = Home
            ) {
                composable<Home> {
                    HomeScreen(rmCharacterViewModel)
                }
                composable<MyCharacter> {
                    MyCharacterScreen(rmCharacterViewModel)
                }
                composable<CreateCharacter> {
                    CreateScreen(rmCharacterViewModel)
                }
                composable<Favorite> {
                    FavoriteScreen(rmCharacterViewModel)
                }
            }
        }
    }
}


@Composable
fun defaultNavigationColors(): NavigationBarItemColors {
    return NavigationBarItemColors(
        selectedIndicatorColor = GreenGlassHex,
        selectedIconColor = Color.Green,
        selectedTextColor = Color.Green,
        unselectedIconColor = Color.Green,
        unselectedTextColor = Color.Green,
        disabledIconColor = Color.Gray,
        disabledTextColor = Color.Gray
    )
}



