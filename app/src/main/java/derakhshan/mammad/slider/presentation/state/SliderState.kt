package derakhshan.mammad.slider.presentation.state

data class SliderState(
    val screens: SliderScreens,
    val radius: Float = 0f,
    val dragDirection: DragDirection = DragDirection.LEFT,
    val animated: Boolean = true
)