package com.umbrella.compose.ui.activities.elements.topics

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun AnimationsScreen() {
    Column {
        SizeAnimationExampleExample()
        VisibilityAnimationExample()
        AnimateColorExample()
        Animate2Params()
        SmoothAnimationExample()
        RotationAnimationExample()
        DraggableAnimationExample()
    }
}

@Composable
fun SizeAnimationExampleExample() {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .background(Color.Blue)
            .animateContentSize()
            .height(if (expanded) 200.dp else 100.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                expanded = !expanded
            }

    ) {
        Text("Animate size")
    }
}

@Composable
fun VisibilityAnimationExample() {
    var visible by remember { mutableStateOf(true) }

    Column {
        AnimatedVisibility(visible = visible) {
            Text("Hello, World!")
        }

        Button(onClick = { visible = !visible }) {
            Text("Animate Visibility")
        }
    }
}

/**
 * animate*AsState
 * */

@Composable
fun AnimateColorExample() {
    var isClicked by remember { mutableStateOf(true) }

    val color by animateColorAsState(
        targetValue = if (isClicked) Color.Red else Color.Green
    )
    Column {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color)
        )

        Button(onClick = { isClicked = !isClicked }) {
            Text("Animate Color")
        }
    }
}


/**
 * updateTransition
 * Предоставляет более сложные анимации, например, для анимации нескольких свойств одновременно.
 * */
@Composable
fun Animate2Params() {
    val state = remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = state.value, label = "transition")

    val size by transition.animateDp(label = "size") { if (state.value) 200.dp else 100.dp }
    val color by transition.animateColor(label = "color") { if (state.value) Color.Red else Color.Blue }

    Box(
        modifier = Modifier
            .size(size)
            .background(color)
            .clickable { state.value = !state.value }
    ) {
        Text("Animate 2 params")
    }
}

/**
 * Animatable
 * Для создания анимации, которая изменяет значения по времени
 * */

@Composable
fun SmoothAnimationExample() {
    val offsetX = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        offsetX.animateTo(
            targetValue = 300f,
            animationSpec = tween(durationMillis = 5000, easing = LinearEasing)
        )
    }

    Box(modifier = Modifier
        .offset(x = offsetX.value.dp)
        .size(100.dp)
        .background(Color.Red))
}

@Composable
fun RotationAnimationExample(){
    var rotation by remember { mutableStateOf(45f) }

    Box(
        modifier = Modifier
            .graphicsLayer(rotationZ = rotation)
            .size(100.dp)
            .background(Color.Green)
            .clickable { rotation += 45f }
    ) {
        Text("Rotate me")
    }
}

@Composable
fun DraggableAnimationExample(){
    val offsetX = remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .size(300.dp)
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState { delta -> offsetX.value += delta }
            )
            .offset(x = offsetX.value.dp)
            .size(100.dp)
            .background(Color.Blue)
    ) {
        Text("Drag me")
    }
}

