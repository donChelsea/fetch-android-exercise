package com.example.fetch_android_exercise.presentation.models

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.android.parcel.Parcelize

@Immutable
@Parcelize
data class ItemUiModel(
    val id: Int = 0,
    val listId: Int = 0,
    val name: String = "",
): Parcelable