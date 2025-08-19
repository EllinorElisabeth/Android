package com.example.myapplication.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.model.CreateRMCharacter
import com.example.myapplication.ui.RMTheme.allroundPadding_16
import com.example.myapplication.ui.RMTheme.allroundPadding_24
import com.example.myapplication.ui.RMTheme.BackgroundWallColor
import com.example.myapplication.ui.RMTheme.bodyTextStyleMedium
import com.example.myapplication.ui.RMTheme.bottomPadding_8
import com.example.myapplication.ui.RMTheme.HotPinkHex
import com.example.myapplication.ui.RMTheme.NeonGreenHex
import com.example.myapplication.ui.RMTheme.startAndTopPadding_8
import com.example.myapplication.ui.RMTheme.TitleBackgroundModifier
import com.example.myapplication.ui.RMTheme.titleStyle
import com.example.myapplication.ui.RMTheme.topAndBottomPadding_16
import com.example.myapplication.ui.RMTheme.horizontalGradientButton
import com.example.myapplication.ui.RMTheme.horizontalGradientInfomation
import com.example.myapplication.ui.RMTheme.topAndBottomPadding_4
import com.example.myapplication.ui.shapes.ButtonShape
import com.example.myapplication.viewmodel.RMCharacterViewModel


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CreateScreen(rmCharacterViewModel: RMCharacterViewModel) {

    var nameInput by remember { mutableStateOf("") }
    var genderInput by remember { mutableStateOf("") }
    var speciesInput by remember { mutableStateOf("") }
    var statusInput by remember { mutableStateOf("") }

    var nameError by remember { mutableStateOf(false) }
    var genderError by remember { mutableStateOf(false) }
    var speciesError by remember { mutableStateOf(false) }
    var statusError by remember { mutableStateOf(false) }

    Column(
        modifier = BackgroundWallColor
    ) {
        Text(
            "Create your own character",
            style = titleStyle,
            modifier = TitleBackgroundModifier.then(allroundPadding_24)
        )

        LazyColumn(
            modifier = allroundPadding_24
                .fillMaxSize()
                .imeNestedScroll()
        ) {
            item {
                Text(
                    "Create your own unique characters here!\n" +
                            "Create however you want!\n" +
                            "The only limit is your imagination!",
                    style = bodyTextStyleMedium
                )
            }
            item {
                Column(
                    modifier = topAndBottomPadding_16
                        .fillMaxWidth()
                        .background(horizontalGradientInfomation)
                        .border(1.dp, HotPinkHex)
                ) {
                    Text(
                        "EXAMPLE:",
                        style = bodyTextStyleMedium,
                        fontWeight = FontWeight(800),
                        modifier = startAndTopPadding_8

                    )
                    Text(
                        "Full Name: Little Ape\n" +
                                "Gender: Genderless\n" +
                                "Species: Alien\n" +
                                "Status: Unknown",
                        style = bodyTextStyleMedium,
                        modifier = startAndTopPadding_8.then(bottomPadding_8)
                    )
                }
            }

            item {
                Text(
                    "Give character a name:",
                    style = bodyTextStyleMedium,
                    modifier = topAndBottomPadding_4
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(bottomPadding_8)
                        .clip(RoundedCornerShape(8.dp)),

                    value = nameInput,
                    onValueChange = {
                        nameInput = it
                        nameError = false
                    },
                    label = { Text("Full Name:") }
                )
                if (nameError) {
                    Text(
                        "Name required",
                        color = Color.Red,
                        modifier = bottomPadding_8
                    )
                }
            }

            item {
                Text(
                    "Choose gender:",
                    style = bodyTextStyleMedium,
                    modifier = topAndBottomPadding_4
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(bottomPadding_8)
                        .clip(RoundedCornerShape(8.dp)),

                    value = genderInput,
                    onValueChange = {
                        genderInput = it
                        genderError = false
                    },
                    label = { Text("Gender:") }
                )
                if (genderError) {
                    Text(
                        "Gender required",
                        color = Color.Red,
                        modifier = bottomPadding_8
                    )
                }
            }

            item {
                Text(
                    "Choose species:",
                    style = bodyTextStyleMedium,
                    modifier = topAndBottomPadding_4
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(bottomPadding_8)
                        .clip(RoundedCornerShape(8.dp)),

                    value = speciesInput,
                    onValueChange = {
                        speciesInput = it
                        speciesError = false
                    },
                    label = { Text("Species:") }
                )
                if (speciesError) {
                    Text(
                        "Species required",
                        color = Color.Red,
                        modifier = bottomPadding_8
                    )
                }
            }

            item {
                Text(
                    "Choose status:",
                    style = bodyTextStyleMedium,
                    modifier = topAndBottomPadding_4
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .then(bottomPadding_8)
                        .clip(RoundedCornerShape(8.dp)),

                    value = statusInput,
                    onValueChange = {
                        statusInput = it
                        statusError = false
                    },
                    label = { Text("Status:") },
                )
                if (statusError) {
                    Text(
                        "Status required",
                        color = Color.Red,
                        modifier = bottomPadding_8
                    )
                }
            }

            item {
                Button(
                    onClick = {
                        if (nameInput.isEmpty()) {
                            nameError = true
                        }
                        if (genderInput.isEmpty()) {
                            genderError = true
                        }
                        if (speciesInput.isEmpty()) {
                            speciesError = true
                        }
                        if (statusInput.isEmpty()) {
                            statusError = true
                        }

                        if (!nameError && !genderError && !speciesError && !statusError) {
                            val newCharacter = CreateRMCharacter(
                                name = nameInput,
                                gender = genderInput,
                                species = speciesInput,
                                status = statusInput,
                                image = null
                            )
                            rmCharacterViewModel.insertCreatedCharacters(newCharacter)

                            nameInput = ""
                            genderInput = ""
                            speciesInput = ""
                            statusInput = ""
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = allroundPadding_16
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .background(horizontalGradientButton, ButtonShape())
                        .border(1.dp, NeonGreenHex, ButtonShape())
                ) {
                    Text("Save", style = bodyTextStyleMedium)
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .height(260.dp)
                )
            }

        }
    }

}


