# AnchoredDraggableExample

This project shows that the androidx.foundation:1.7.0-rc01 has an error in AnchoredDraggable.

In the file Swiper.kt:L50 this code is present.
```kotlin
state.anchoredDraggableState.updateAnchors(
    newAnchors = DraggableAnchors {
        false at 0f
        true at fullSwipePx //.times(2) // <- This works
    },
)
```

When running the code, the "true" anchor triggers just when we pass the halfway point.
By multiplying the width by 2, everything works as expected.

