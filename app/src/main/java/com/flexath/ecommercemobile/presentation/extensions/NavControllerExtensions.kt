package com.flexath.ecommercemobile.presentation.extensions

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController

val NavHostController.canGoBack: Boolean
    get() = this.currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED

val enterPush: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    fadeIn(
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutSlowInEasing
        )
    ) + slideIntoContainer(
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutSlowInEasing
        ),
        towards = AnimatedContentTransitionScope.SlideDirection.Start,
        initialOffset = { it }
    )
}
val exitPush: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    fadeOut(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        towards = AnimatedContentTransitionScope.SlideDirection.End,
        targetOffset = { -it / 4 }
    )
}
val enterPop: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    fadeIn(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        )
    ) + slideIntoContainer(
        animationSpec = tween(
            durationMillis = 300,
            easing = FastOutSlowInEasing
        ),
        towards = AnimatedContentTransitionScope.SlideDirection.Start,
        initialOffset = { -it / 4 }
    )
}
val exitPop: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    fadeOut(
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutSlowInEasing
        )
    ) + slideOutOfContainer(
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutSlowInEasing
        ),
        towards = AnimatedContentTransitionScope.SlideDirection.End,
        targetOffset = { it }
    )
}