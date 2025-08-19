package com.example.myapplication


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.data.room.AppDbRepository
import com.example.myapplication.navigation.BottomNavigation
import com.example.myapplication.navigation.TopBar
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.viewmodel.RMCharacterViewModel


class MainActivity : ComponentActivity() {

    private val repository by lazy { AppDbRepository.apply { initializeDatabase(applicationContext) } }

    private val _rmCharacterViewModel by lazy { RMCharacterViewModel(repository) }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppDbRepository.initializeDatabase(applicationContext)

        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(modifier = Modifier.fillMaxWidth()) {
                        Column {
                            TopBar()
                            BottomNavigation(
                                rmCharacterViewModel = _rmCharacterViewModel
                            )
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun Preview() {
    MyApplicationTheme {
        TopBar()
    }
}



