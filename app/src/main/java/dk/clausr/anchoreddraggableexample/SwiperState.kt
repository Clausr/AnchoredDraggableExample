package dk.clausr.anchoreddraggableexample

import androidx.compose.animation.core.exponentialDecay
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@OptIn(ExperimentalFoundationApi::class)
class SwiperState(
    initiallySwiped: Boolean = false,
    val onSwiped: () -> Unit,
) {
    val anchoredDraggableState = AnchoredDraggableState(
        initialValue = initiallySwiped,
        positionalThreshold = { totalDistance: Float ->
            totalDistance
        },
        velocityThreshold = { Float.MAX_VALUE },
        confirmValueChange = { newValue: Boolean ->
            if (newValue && !currentValue) {
                onSwiped()
            }
            true
        },
        snapAnimationSpec = spring(),
        decayAnimationSpec = exponentialDecay(),
    )
    private val currentValue: Boolean
        get() = anchoredDraggableState.currentValue


    suspend fun reset() {
        anchoredDraggableState.animateTo(targetValue = false)
    }
}

@Composable
fun rememberSwiperState(
    initiallySwiped: Boolean = false,
    onSwipe: () -> Unit,
): SwiperState = remember {
    SwiperState(initiallySwiped, onSwipe)
}
