package derakhshan.mammad.slider.presentation.view_model

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import derakhshan.mammad.slider.presentation.event.SliderEvent
import derakhshan.mammad.slider.presentation.state.DragDirection
import derakhshan.mammad.slider.presentation.state.SliderScreens
import derakhshan.mammad.slider.presentation.state.SliderState
import derakhshan.mammad.slider.presentation.state.ViewModelState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SliderViewModel(
    private val sliderItems: List<@Composable () -> Unit>,
    private val screenWidth: Int
) : ViewModel() {
    private val viewModelState = ViewModelState()

    private val _state =
        MutableStateFlow(
            SliderState(
                screens = SliderScreens(
                    currentScreen = sliderItems.first(),
                    nextScreen = sliderItems.getOrNull(1)
                )
            )
        )
    val state = _state.asStateFlow()


    fun uiEvent(event: SliderEvent) {
        when (event) {
            SliderEvent.OnAnimationFinished -> {
                Log.i("log", "uiEvent: called animation finished and ${state.value.animated}")
                viewModelState.isAnimationFinished = true
                if (viewModelState.isDragFinished) {
                    viewModelState.updateDirection = true
                    _state.update {
                        it.copy(
                            screens = it.screens.copy(currentScreen = sliderItems[viewModelState.currentScreenIndex]),
                            animated = false,
                            radius = 0f
                        )
                    }
                }
            }

            SliderEvent.OnDragStopped -> {
                viewModelState.isDragFinished = true
                viewModelState.isDragComplete = state.value.radius >= screenWidth / 2f
                if (viewModelState.isDragComplete) {
                    viewModelState.currentScreenIndex =
                        (viewModelState.currentScreenIndex + if (state.value.dragDirection == DragDirection.LEFT) 1 else -1).coerceIn(
                            maximumValue = sliderItems.lastIndex,
                            minimumValue = 0
                        )
                    _state.update {
                        it.copy(
                            //screens = it.screens.copy(currentScreen = sliderItems[viewModelState.currentScreenIndex]),
                            radius = screenWidth.toFloat()
                        )
                    }
                } else {
                    _state.update { it.copy(radius = 0f) }
                }
                if (viewModelState.isAnimationFinished)
                    viewModelState.updateDirection = true
            }

            is SliderEvent.UpdateRadius -> {
                viewModelState.isDragFinished = false
                if (state.value.animated.not())
                    _state.update { it.copy(animated = true) }
                val direction = if (event.radius < 0) DragDirection.LEFT else DragDirection.RIGHT
                if (viewModelState.updateDirection) {
                    _state.update {
                        it.copy(
                            dragDirection = direction,
                            screens = it.screens.copy(
                                nextScreen = sliderItems.getOrNull(viewModelState.currentScreenIndex + if (direction == DragDirection.LEFT) (1) else (-1))
                            )
                        )
                    }
                    viewModelState.updateDirection = false
                }
                when (state.value.dragDirection) {
                    DragDirection.LEFT -> {
                        if (viewModelState.currentScreenIndex != sliderItems.lastIndex)
                            _state.update {
                                it.copy(
                                    radius = (state.value.radius + (-event.radius * 1.3f)).coerceIn(
                                        minimumValue = 0f,
                                        maximumValue = screenWidth.toFloat()
                                    )
                                )
                            }
                    }

                    DragDirection.RIGHT -> {
                        if (viewModelState.currentScreenIndex != 0)
                            _state.update {
                                it.copy(
                                    radius = (state.value.radius + (event.radius * 1.3f)).coerceIn(
                                        minimumValue = 0f,
                                        maximumValue = screenWidth.toFloat()
                                    )
                                )
                            }
                    }
                }
            }
        }
    }


}







