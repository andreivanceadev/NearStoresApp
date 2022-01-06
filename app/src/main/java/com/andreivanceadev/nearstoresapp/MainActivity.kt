package com.andreivanceadev.nearstoresapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.andreivanceadev.stores.view.StoresScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NearStoresApp()
        }
    }
}

@Composable
private fun NearStoresApp() {
    StoresScreen()
}