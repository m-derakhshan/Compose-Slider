package derakhshan.mammad.slider.presentation.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import derakhshan.mammad.slider.presentation.state.DragDirection
import derakhshan.mammad.slider.presentation.state.DragDirection.*

class SliderShape(
    private val radius: Float,
    private val direction: DragDirection
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path =
            when (direction) {
                RIGHT -> toRightDrawPath(radius = radius)
                LEFT -> toLeftDrawPath(size = size, radius = radius)
            }
        )
    }

    private fun toLeftDrawPath(size: Size, radius: Float): Path {

        return Path().apply {
            addOval(
                Rect(
                    center = Offset(x = size.width, y = size.height),
                    radius = radius * 2.5f
                )
            )
        }
    }

    private fun toRightDrawPath(radius: Float): Path {
        return Path().apply {
            addOval(
                Rect(
                    center = Offset(x = 0f, y = 0f),
                    radius = radius * 2.5f
                )
            )
        }
    }


}