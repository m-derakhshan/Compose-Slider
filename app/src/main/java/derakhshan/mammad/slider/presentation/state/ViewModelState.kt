package derakhshan.mammad.slider.presentation.state

data class ViewModelState(
    var isDragFinished: Boolean = true,
    var isAnimationFinished: Boolean = true,
    var isDragComplete: Boolean = false,
    var updateDirection: Boolean = true,
    var currentScreenIndex: Int = 0
)
