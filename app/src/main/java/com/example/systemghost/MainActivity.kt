package com.example.systemghost

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.systemghost.data.PreferencesManager
import com.example.systemghost.data.database.SystemGhostDatabase
import com.example.systemghost.data.repository.IdentityRepository
import com.example.systemghost.ui.MainViewModel
import com.example.systemghost.ui.SystemGhostApp
import com.example.systemghost.ui.theme.SystemGhostTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = SystemGhostDatabase.getDatabase(this)
        val repository = IdentityRepository(database.systemGhostDao())
        val prefs = PreferencesManager(this)
        val viewModel = MainViewModel(repository, prefs)

        setContent {
            SystemGhostTheme {
                SystemGhostApp(viewModel)
            }
        }
    }
}
