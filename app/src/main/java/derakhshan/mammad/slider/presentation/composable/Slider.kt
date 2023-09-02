package derakhshan.mammad.slider.presentation.composable

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import derakhshan.mammad.slider.presentation.event.SliderEvent
import derakhshan.mammad.slider.presentation.utils.SliderShape
import derakhshan.mammad.slider.presentation.view_model.SliderViewModel
import derakhshan.mammad.slider.presentation.view_model.SliderViewModelFactory

@Composable
fun Slider(
    modifier: Modifier = Modifier,
    items: List<@Composable () -> Unit>
) {

    val screenWidth =
        with(LocalDensity.current) { LocalConfiguration.current.screenWidthDp.dp.roundToPx() }

    val viewModel: SliderViewModel =
        viewModel(factory = SliderViewModelFactory(items = items, screenWidth = screenWidth))
    val state = viewModel.state.collectAsState().value

    val animatedRadius by animateFloatAsState(
        targetValue = state.radius,
        label = "radius animation",
        finishedListener = { viewModel.uiEvent(SliderEvent.OnAnimationFinished) }
    )
    val animatedAlpha by animateFloatAsState(
        targetValue = 1 - (state.radius / screenWidth),
        label = "alpha animation"
    )
    Box(
        modifier = modifier.draggable(
            state = rememberDraggableState(onDelta = {
                viewModel.uiEvent(SliderEvent.UpdateRadius(radius = it))
            }),
            orientation = Orientation.Horizontal,
            onDragStopped = { viewModel.uiEvent(SliderEvent.OnDragStopped) },
        )
    ) {
        Box(modifier = Modifier.alpha(if (state.animated) animatedAlpha else 1f)) {
            state.screens.currentScreen.invoke()
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    shape = SliderShape(
                        radius = if (state.animated) animatedRadius else state.radius,
                        direction = state.dragDirection
                    )
                )
        ) {
            state.screens.nextScreen?.invoke()
        }

    }
}

