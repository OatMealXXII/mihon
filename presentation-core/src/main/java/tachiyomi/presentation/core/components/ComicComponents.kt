package tachiyomi.presentation.core.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/**
 * Custom Shape that draws a comic dialog speech bubble with a bottom-left tail.
 */
class ComicSpeechBubbleShape(private val cornerRadius: Float = 24f) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val w = size.width
            val h = size.height
            val bubbleHeight = h - 12f // Leave space for tail

            // Add the main body round rect
            addRoundRect(
                RoundRect(
                    left = 0f,
                    top = 0f,
                    right = w,
                    bottom = bubbleHeight,
                    radiusX = cornerRadius,
                    radiusY = cornerRadius
                )
            )

            // Draw a cute tail at the bottom left
            moveTo(24f, bubbleHeight)
            lineTo(16f, h)
            lineTo(36f, bubbleHeight)
            close()
        }
        return Outline.Generic(path)
    }
}

/**
 * Custom Shape that draws an asymmetric comic panel with slightly skewed corners.
 */
class ComicPanelShape(
    private val topLeftOffset: Float = 0f,
    private val topRightOffset: Float = 8f,
    private val bottomRightOffset: Float = 4f,
    private val bottomLeftOffset: Float = 12f
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            val w = size.width
            val h = size.height
            moveTo(topLeftOffset, 0f)
            lineTo(w - topRightOffset, 0f)
            lineTo(w, h - bottomRightOffset)
            lineTo(bottomLeftOffset, h)
            close()
        }
        return Outline.Generic(path)
    }
}

/**
 * Modifier to apply a thick comic-book style black border with a solid black offset drop shadow.
 */
fun Modifier.comicBorder(
    shape: Shape = RoundedCornerShape(8.dp),
    borderWidth: Dp = 2.dp,
    shadowOffset: Dp = 4.dp,
    shadowColor: Color = Color.Black
): Modifier = this
    .drawBehind {
        val offsetPx = shadowOffset.toPx()
        drawContext.canvas.save()
        drawContext.canvas.translate(offsetPx, offsetPx)
        val outline = shape.createOutline(size, layoutDirection, this)
        val path = Path().apply {
            when (outline) {
                is Outline.Rectangle -> addRect(outline.rect)
                is Outline.Rounded -> addRoundRect(outline.roundRect)
                is Outline.Generic -> addPath(outline.path)
            }
        }
        drawPath(path = path, color = shadowColor)
        drawContext.canvas.restore()
    }
    .border(width = borderWidth, color = Color.Black, shape = shape)

/**
 * Creates an animated linear gradient representing manga halftone screen-tone/hatching stripes.
 */
@Composable
fun comicShimmerBrush(): Brush {
    val transition = rememberInfiniteTransition(label = "comic_shimmer")
    val offset = transition.animateFloat(
        initialValue = 0f,
        targetValue = 200f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500)
        ),
        label = "offset"
    )
    val color1 = MaterialTheme.colorScheme.surfaceContainerHigh
    val color2 = MaterialTheme.colorScheme.surfaceContainerHighest
    
    return Brush.linearGradient(
        colors = listOf(color1, color1, color2, color2, color1),
        start = androidx.compose.ui.geometry.Offset(offset.value, offset.value),
        end = androidx.compose.ui.geometry.Offset(offset.value + 100f, offset.value + 100f),
        tileMode = TileMode.Repeated
    )
}

/**
 * A loading skeleton box that displays the animated manga screentone shimmer.
 */
@Composable
fun ComicLoadingPanel(
    modifier: Modifier = Modifier,
    shape: Shape = ComicPanelShape()
) {
    Box(
        modifier = modifier
            .comicBorder(shape = shape)
            .background(comicShimmerBrush(), shape = shape)
    )
}
