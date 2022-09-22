package com.martinz.myshoe.util

import androidx.navigation.NavDirections
import androidx.navigation.Navigation

sealed class UiEvent {

    data class ShowToast(val message : String) : UiEvent()
    object NavigateUp : UiEvent()
}
