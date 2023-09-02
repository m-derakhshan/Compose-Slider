package derakhshan.mammad.slider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import derakhshan.mammad.slider.presentation.composable.Slider
import derakhshan.mammad.slider.ui.theme.SliderTheme

class MainActivity : ComponentActivity() {
    private val images = listOf(
        R.mipmap.image1,
        R.mipmap.image2,
        R.mipmap.image3,
        R.mipmap.image4,
        R.mipmap.image5
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SliderTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Slider(items = images.map {
                        {
                            Image(
                                painter = painterResource(id = it),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    })
                }
            }
        }
    }
}
