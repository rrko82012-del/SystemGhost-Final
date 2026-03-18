package com.example.systemghost.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.example.systemghost.ui.screens.HomeScreen
import com.example.systemghost.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SystemGhostApp(viewModel: MainViewModel) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = CyberSurface,
                drawerContentColor = CyberSilver
            ) {
                Spacer(Modifier.height(24.dp))
                Text("SystemGhost Menu", modifier = Modifier.padding(16.dp), color = NeonGreen)
                HorizontalDivider(color = CyberSilver.copy(alpha = 0.2f))
                NavigationDrawerItem(
                    label = { Text("قاعدة البيانات المحلية") },
                    selected = false,
                    onClick = { /* TODO: Navigate to Database */ },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = CyberSurface)
                )
                NavigationDrawerItem(
                    label = { Text("سجل التغييرات") },
                    selected = false,
                    onClick = { /* TODO: Navigate to Changelog */ },
                    colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = CyberSurface)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("SystemGhost", color = NeonGreen) },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = CyberBackground),
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu", tint = CyberSilver)
                        }
                    }
                )
            }
        ) { innerPadding ->
            HomeScreen(modifier = Modifier.padding(innerPadding), viewModel = viewModel)
        }
    }
}
