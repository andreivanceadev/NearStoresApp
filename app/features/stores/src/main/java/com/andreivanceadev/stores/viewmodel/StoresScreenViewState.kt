package com.andreivanceadev.stores.viewmodel

import android.os.Parcelable
import com.andreivanceadev.stores.model.StoreModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoresScreenViewState(
    val stores: List<StoreModel> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String = ""
) : Parcelable