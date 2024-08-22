package dk.clausr.anchoreddraggableexample

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Swiper(
    text: String,
    state: SwiperState,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    val swiperSize = 64.dp

    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd,
    ) {
        val swiperWidthPx = constraints.maxWidth
        val swiperWidth = with(LocalDensity.current) { swiperWidthPx.toDp() }
        val thumbSizePx = with(LocalDensity.current) { swiperSize.toPx() }
        val fullSwipePx = swiperWidthPx - thumbSizePx

        state.anchoredDraggableState.updateAnchors(
            newAnchors = DraggableAnchors {
                false at 0f
                true at fullSwipePx //.times(2) // <- This works
            },
        )

        val swipeOffset = with(LocalDensity.current) {
            state.anchoredDraggableState.offset.coerceIn(0f..fullSwipePx).toDp()
        }

        val textAlpha: Float = ((thumbSizePx - state.anchoredDraggableState.offset) / thumbSizePx).coerceIn(0f, 1f)

        Surface(
            modifier = Modifier
                .width(swiperWidth - swipeOffset)
                .height(swiperSize),
            shape = CircleShape,
            color = Color.Blue,
        ) {
            Box {
                Box(
                    modifier = Modifier
                        .padding(3.dp)
                        .aspectRatio(1f)
                        .fillMaxHeight()
                        .clip(CircleShape)
                        .background(Color.White)
                        .anchoredDraggable(
                            state = state.anchoredDraggableState,
                            orientation = Orientation.Horizontal,
                            enabled = enabled && !state.anchoredDraggableState.currentValue,
                        ),
                    contentAlignment = Alignment.Center,
                ) {
                    SwiperThumbArrow(color = Color.Blue)
                }
            }
        }

        Text(
            modifier = Modifier
                .padding(start = swiperSize, end = 48.dp)
                .align(Alignment.Center)
                .alpha(textAlpha),
            color = Color.White,
            text = text,
        )
    }
}

@Composable
private fun SwiperThumbArrow(
    color: Color,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_launcher_foreground),
        contentDescription = null,
        colorFilter = ColorFilter.tint(color),
    )
}
