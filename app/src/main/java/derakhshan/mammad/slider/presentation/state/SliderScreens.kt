package derakhshan.mammad.slider.presentation.state

import androidx.compose.runtime.Composable

data class SliderScreens(
    val currentScreen: @Composable () -> Unit,
    val nextScreen: (@Composable () -> Unit)? = null
)
