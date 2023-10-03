package com.ramseys.jobplan.Composables.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetDefaults.Elevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CollapsingToolbar(
    @DrawableRes backgroundImageResId: Int,
    progress: Float,
    onPrivacyTipButtonClicked: () -> Unit,
    onSettingsButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {


    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
    ) {
        Box (modifier = Modifier.fillMaxSize()) {

        }
    }
}