package com.ramseys.jobplan.Composables.DataClassRep

import androidx.compose.ui.graphics.Color

data class StackedData(
    val inputs: List<Float>,
    val colors: List<Color>,
    val names: List<String>
)
