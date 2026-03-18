package com.example.systemghost.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.systemghost.ui.MainViewModel
import com.example.systemghost.ui.components.InfoCard
import com.example.systemghost.ui.components.MainToggleButton
import com.example.systemghost.ui.theme.CyberBackground

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val isProtected by viewModel.isProtected.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CyberBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // الزر المركزي
        MainToggleButton(
            isProtected = isProtected,
            onClick = { viewModel.toggleProtection() }
        )

        Spacer(modifier = Modifier.height(48.dp))

        // شبكة البطاقات (Cards Grid)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            item { InfoCard("IP Address", if(isProtected) "192.168.x.x (Spoofed)" else "Real IP", Icons.Filled.Wifi) }
            item { InfoCard("MAC Address", if(isProtected) "02:00:00:00:00:00" else "Real MAC", Icons.Filled.SettingsEthernet) }
            item { InfoCard("IMEI", if(isProtected) "Hidden" else "Real IMEI", Icons.Filled.PhoneAndroid) }
            item { InfoCard("GPS Spoofer", if(isProtected) "Active" else "Inactive", Icons.Filled.LocationOn) }
        }
    }
}
