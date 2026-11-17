package com.example.art_space_bayu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspacebayu.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

// Data class untuk menyimpan informasi karya seni
data class Artwork(
    @DrawableRes val imageRes: Int,
    val title: String,
    val artist: String,
    val year: String
)

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {

    val artworks = listOf(
        Artwork(
            imageRes = R.drawable.screenshot_2026_11_09_101835,
            title = "The Scream",
            artist = "Edvard Munch",
            year = "1893"
        ),
        Artwork(
            imageRes = R.drawable.screenshot_2026_11_09_101814,
            title = "Sunflowers",
            artist = "Vincent van Gogh",
            year = "1888"
        ),
        Artwork(
            imageRes = R.drawable.screenshot_2026_11_09_101853,
            title = "Cafe Terrace at Night",
            artist = "Vincent van Gogh",
            year = "1888"
        ),
        Artwork(
            imageRes = R.drawable.screenshot_2026_11_09_102549,
            title = "Monalisa",
            artist = "Leonardo da Vinci",
            year = "1503-06"
        ),
        Artwork(
            imageRes = R.drawable.screenshot_2026_11_09_102658,
            title = " Salvatore Mundi",
            artist = " Leonardo da Vinci",
            year = "1499-1510"
        )
    )

    // State untuk menyimpan posisi indeks gambar aktif
    var currentIndex by remember { mutableIntStateOf(0) }
    val currentArtwork = artworks[currentIndex]

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Kontainer Gambar (Wall Display)
        Surface(
            modifier = Modifier
                .shadow(8.dp)
                .padding(24.dp),
            color = Color.White
        ) {
            Image(
                painter = painterResource(id = currentArtwork.imageRes),
                contentDescription = currentArtwork.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp),
                contentScale = ContentScale.Fit
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Kontainer Deskripsi Artwork
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF3F3F3))
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = currentArtwork.title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Light
            )
            Row {
                Text(
                    text = currentArtwork.artist,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
                Text(
                    text = " (${currentArtwork.year})",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tombol Navigasi (Previous & Next)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    if (currentIndex > 0) {
                        currentIndex--
                    } else {
                        currentIndex = artworks.size - 1
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Previous")
            }

            Spacer(modifier = Modifier.width(16.dp))

            Button(
                onClick = {
                    if (currentIndex < artworks.size - 1) {
                        currentIndex++
                    } else {
                        currentIndex = 0
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Next")
            }
        }
    }
}
@androidx.compose.ui.tooling.preview.Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    MaterialTheme {
        ArtSpaceScreen()
    }
}