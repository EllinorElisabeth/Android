package com.example.myapplication.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.ui.RMTheme.allroundPadding_16
import com.example.myapplication.ui.RMTheme.OrangeHex
import com.example.myapplication.ui.RMTheme.logoTextStyle
import com.example.myapplication.ui.RMTheme.verticalGradientLogo


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {

    TopAppBar(
        navigationIcon = {

            Box(
                modifier = allroundPadding_16
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(brush = verticalGradientLogo)
                    .border(1.dp, OrangeHex, CircleShape)
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ufo), /* <a href="https://www.flaticon.com/free-icons/ufo" title="ufo icons">Ufo icons created by smashingstocks - Flaticon</a> */
                    "Logo"
                )
            }
        },
        title = { Text("Rick and Morty", style = logoTextStyle) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black,
            titleContentColor = Color.Green,
            navigationIconContentColor = Color.Black
        )
    )
}