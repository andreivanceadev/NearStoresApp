package com.andreivanceadev.stores.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.andreivanceadev.stores.model.StoresRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StoresViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val storesRepository: StoresRepository,
) : ContainerHost<StoresScreenViewState, StoresSideEffects>, ViewModel() {

    override val container: Container<StoresScreenViewState, StoresSideEffects> = container(
        initialState = StoresScreenViewState(),
        savedStateHandle = savedStateHandle
    ) {
        requestNeededPermissions()
    }

    private fun requestNeededPermissions() = intent {
        postSideEffect(RequestLocationPermission)
    }

    fun loadStores(lat: Float, lon: Float) = intent {
        reduce { state.copy(isLoading = true) }
        val storesResponse = storesRepository.getStores(lat, lon)

        if (storesResponse.first != null) {
            reduce { state.copy(isLoading = false, stores = storesResponse.first!!) }
        } else {
            reduce { state.copy(isLoading = false, hasError = true, errorMessage = storesResponse.second!!) }
        }
    }
}
