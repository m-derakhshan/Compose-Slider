package derakhshan.mammad.slider.presentation.event

sealed interface SliderEvent {
    object OnAnimationFinished : SliderEvent
    data class UpdateRadius(val radius: Float) : SliderEvent
    object OnDragStopped : SliderEvent
}