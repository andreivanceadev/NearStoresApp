package com.andreivanceadev.stores.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.andreivanceadev.stores.model.StoreModel

@Composable
@Preview
private fun PreviewStoresScreen() {
    StoresScreenView(
        mockedStoreModel
    )
}

val mockedStoreModel = StoreModel(
    "Mocked Name",
    "Mocked Address",
    "123456",
    "Mocked City"
)

@Composable
private fun StoresScreenView(
    viewState: StoreModel
) {

}

@Composable
private fun StoreItem(){

}