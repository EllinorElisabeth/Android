import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import com.example.myapplication.R
import com.example.myapplication.data.model.RMCharacter
import com.example.myapplication.ui.RMTheme.bodyTextStyleMedium
import com.example.myapplication.ui.RMTheme.bodyTextStyleSmall
import com.example.myapplication.ui.RMTheme.HotPinkHex
import com.example.myapplication.ui.RMTheme.NeonGreenHex
import com.example.myapplication.ui.RMTheme.subTitleStyle
import com.example.myapplication.ui.RMTheme.topAndBottomPadding_16
import com.example.myapplication.ui.RMTheme.horizontalGradientButton
import com.example.myapplication.ui.RMTheme.horizontalGradientWarm
import com.example.myapplication.ui.RMTheme.horizontalGradientNeon
import com.example.myapplication.ui.RMTheme.horizontalGradientInfomation
import com.example.myapplication.ui.RMTheme.topAndBottomPadding_4
import com.example.myapplication.ui.shapes.BorderShapeOne
import com.example.myapplication.ui.shapes.BorderShapeTwo
import com.example.myapplication.ui.shapes.ButtonShape


@Composable
fun CharacterItem(
    character: RMCharacter,
    characterInFavorite: Boolean,
    favoriteIcon: Boolean,
    deleteFavoriteIcon: Boolean,
    deleteCreatedCharacterIcon: Boolean,
    addToFavoriteBtn: () -> Unit = {},
    deleteCreatedCharacterBtn: () -> Unit = {},
    deleteCharacterFromFavoriteBtn: () -> Unit = {},
) {

    val showInfo = remember { mutableStateOf(false) }

    Box(
        modifier = topAndBottomPadding_16
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .border(2.dp, horizontalGradientWarm, BorderShapeOne())
                .border(2.dp, horizontalGradientNeon, BorderShapeTwo())
                .zIndex(0f)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (character.image.isNullOrEmpty()) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.outline_image_24),
                            "Character has no image",
                            modifier = Modifier
                                .size(40.dp),
                            tint = Color.White
                        )
                        Text(
                            "No image",
                            style = bodyTextStyleSmall,
                            modifier = Modifier.padding(0.dp)
                        )
                    }
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(character.image),
                        "Character image",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                }
                Row(
                    modifier = Modifier
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        character.name,
                        style = subTitleStyle
                    )
                }
                Row {
                    if (favoriteIcon) {
                        IconButton(onClick = { addToFavoriteBtn() }) {
                            Icon(
                                imageVector = if (characterInFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                if (!characterInFavorite) "Added to favorite" else "Fail to add",
                                tint = Color.Red
                            )
                        }
                    }
                    if (deleteFavoriteIcon) {
                        IconButton(onClick = { deleteCharacterFromFavoriteBtn() }) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                "Delete Icon",
                                tint = Color.White,
                            )
                        }
                    }
                    if (deleteCreatedCharacterIcon) {
                        IconButton(onClick = { deleteCreatedCharacterBtn() }) {
                            Icon(
                                imageVector = Icons.Outlined.Delete,
                                "Delete Icon",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { showInfo.value = !showInfo.value },
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = topAndBottomPadding_4
                        .background(horizontalGradientButton, ButtonShape())
                        .border(1.dp, NeonGreenHex, ButtonShape())
                ) {
                    Text(
                        if (showInfo.value) "Hide Info" else "Show Info",
                        style = bodyTextStyleMedium
                    )
                }
                if (showInfo.value) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 8.dp, 0.dp, 0.dp)
                            .background(horizontalGradientInfomation)
                            .border(1.dp, HotPinkHex)
                            .padding(16.dp)

                    ) {
                        Text("Gender: ${character.gender}", style = bodyTextStyleMedium)
                        Text("Species: ${character.species}", style = bodyTextStyleMedium)
                        Text("Status: ${character.status}", style = bodyTextStyleMedium)
                    }
                }
            }
        }
    }

}
