# Flexible Composable Slider for Android Apps

This is a flexible and easy-to-use slider component for displaying a series of images in your Android application. You can pass any composable function as a page of the slider, making it highly customizable.

## Features

- Easily create sliders in your Android app.
- Highly flexible and customizable using Jetpack Compose.
- Supports displaying any composable function as a slider page.

## Usage

To use the slider, follow these steps:

1. Create a list of composable functions that you want to display in the slider.

2. Initialize the slider with these items and any desired customization using the `Slider` composable.


```kotlin
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
```
## Demo

[Watch a demo of the Slider on YouTube](https://youtube.com/shorts/T65MM-V8zfc?feature=share)
