package com.example.fetch_android_exercise.presentation.models

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.android.parcel.Parcelize

@Immutable
@Parcelize
data class ItemGroupUiModel(
    val id: Int = 0,
    val items: List<ItemUiModel> = emptyList(),
): Parcelable
