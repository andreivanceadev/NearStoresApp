package com.andreivanceadev.stores.view

import android.Manifest
import android.content.pm.PackageManager.PERMISSION_GRANTED
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.andreivanceadev.stores.getLocationCoordinates
import com.andreivanceadev.stores.model.StoreModel
import com.andreivanceadev.stores.styles.Dimens
import com.andreivanceadev.stores.viewmodel.RequestLocationPermission
import com.andreivanceadev.stores.viewmodel.StoresScreenViewState
import com.andreivanceadev.stores.viewmodel.StoresViewModel
import kotlinx.coroutines.flow.collect

@Composable
@Preview
private fun PreviewStoresScreen() {
    StoresScreenView(
        StoresScreenViewState(stores = mockedStoreModel)
    )
}

val mockedStoreModel = listOf(
    StoreModel(
        "Mocked Name",
        "Mocked Address",
        "123456",
        "Mocked City"
    )
)

@Composable
fun StoresScreen(
    viewModel: StoresViewModel = hiltViewModel()
) {

    val viewState = viewModel.container.stateFlow.collectAsState()

    val context = LocalContext.current

    val requestPermissionLauncher = requestPermissionLauncher(
        onGranted = {
            val (lat, lon) = getLocationCoordinates(context)
            viewModel.loadStores(lat, lon)
        },
        onDenied = { }
    )

    StoresScreenView(viewState = remember { viewState }.value)

    LaunchedEffect(key1 = null) {
        viewModel.container.sideEffectFlow.collect {
            when (it) {
                RequestLocationPermission -> {

                    if (ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) == PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        ) == PERMISSION_GRANTED
                    ) {
                        val (lat, lon) = getLocationCoordinates(context)
                        viewModel.loadStores(lat, lon)
                    }

                    val permissions = arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                    requestPermissionLauncher.launch(permissions)
                }
            }
        }
    }

}


@Composable
fun requestPermissionLauncher(
    onGranted: () -> Unit,
    onDenied: () -> Unit
): ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>> {

    return rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->

        val granted = permissions.entries.all {
            it.value
        }

        if (granted) {
            onGranted()
        } else {
            onDenied()
        }
    }
}

@Composable
private fun StoresScreenView(
    viewState: StoresScreenViewState
) {

    if (viewState.isLoading) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
    }

    if (viewState.hasError) {
        MiddleScreenText(viewState.errorMessage)
    } else if (viewState.stores.isEmpty() && !viewState.isLoading) {
        MiddleScreenText(text = "No stores found in the area")
    } else {
        LazyColumn {
            items(viewState.stores) { store ->
                StoreItem(store)
            }
        }
    }
}

@Composable
fun MiddleScreenText(text: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)
    }
}

@Composable
@Preview
private fun StoreItem(storeModel: StoreModel = mockedStoreModel.first()) {
    Card(
        modifier = Modifier
            .width(344.dp)
            .padding(bottom = Dimens.space_x1),
        shape = RoundedCornerShape(Dimens.space_x1)
    ) {
        StoreContent(
            modifier = Modifier.padding(
                start = Dimens.space_x2,
                end = Dimens.space_x2,
                top = Dimens.space_x2,
                bottom = Dimens.space_x2
            ),
            storeModel = storeModel
        )
    }
}

@Composable
private fun StoreContent(
    modifier: Modifier,
    storeModel: StoreModel
) {
    Column(modifier = modifier) {
        Text(
            text = storeModel.name,
            style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.height(Dimens.space_x2))
        Text(
            text = storeModel.address,
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(Dimens.space_x2))
        Text(
            text = storeModel.postalCode,
            style = MaterialTheme.typography.body2
        )
        Spacer(modifier = Modifier.height(Dimens.space_x2))
        Text(
            text = storeModel.city,
            style = MaterialTheme.typography.body2
        )
    }
}
