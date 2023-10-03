package com.ramseys.jobplan.Composables.DataClassRep

sealed class State {
    object START : State()
    object LOADING : State()
}