package com.example.mysoundboardatla

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mysoundboardatla.ui.theme.MySoundboardATLATheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySoundboardATLATheme {
                App()
            }
        }
    }
}

val background = Color(0xFF3268B8)
val primary = Color(0xFF0D0E12)
val secondary = Color(0xFFFCFDFB)
val tertiary = Color(0xFFFA8500)

data class SoundBoardItem(
    val text: String, val audio: Int
)

val SoundBoardItems = listOf(
    SoundBoardItem("Water",  R.raw.note01),
    SoundBoardItem("Earth",  R.raw.note02),
    SoundBoardItem("Fire",  R.raw.note03),
    SoundBoardItem("Air", R.raw.note04),
    SoundBoardItem("Harmony",  R.raw.note05),
    SoundBoardItem("Fire Nation",  R.raw.note06),
    SoundBoardItem("Stop",  R.raw.note07),
    SoundBoardItem("Vanished",  R.raw.note08),
    SoundBoardItem("Discovered",  R.raw.note09),
    SoundBoardItem("Aang", R.raw.note10),
    SoundBoardItem("Oof", R.raw.note11),
    SoundBoardItem("World",  R.raw.note12),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Soundboard: ATLA", color = secondary) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = background)) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding).background(color = background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val currentContext = LocalContext.current

            @Composable
            fun SoundButton(text: String, audio: Int, modifier: Modifier = Modifier.padding(4.dp).weight(.1f)) {
                Button(
                    onClick = {
                        val musicPlayer = MediaPlayer.create(currentContext, audio)
                        musicPlayer.start()
                    },
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = primary),
                    modifier = modifier.size(60.dp)
                ) { Text(text, color = tertiary) }
            }


            Box(modifier = Modifier.fillMaxWidth().height(400.dp).background(primary)) {
                Image(
                    painterResource(R.drawable.img),
                    contentDescription = "Image of Avatar",
                    modifier = Modifier.fillMaxSize()
                )
            }

            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxSize()
                    .weight(.1f)
            ) {
                for ((text, audio) in SoundBoardItems) {
                    item { SoundButton(text, audio) }
                }
            }

            val ending = SoundBoardItem("Ending", R.raw.note13)
            SoundButton(ending.text, ending.audio, modifier = Modifier.fillMaxWidth())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MySoundboardATLATheme {
        App()
    }
}