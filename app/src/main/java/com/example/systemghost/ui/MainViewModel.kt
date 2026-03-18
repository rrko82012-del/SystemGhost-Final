package com.example.systemghost.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.systemghost.data.PreferencesManager
import com.example.systemghost.data.repository.IdentityRepository
import com.example.systemghost.utils.IdentitySimulator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: IdentityRepository,
    private val prefs: PreferencesManager
) : ViewModel() {

    private val _isProtected = MutableStateFlow(prefs.isProtected())
    val isProtected: StateFlow<Boolean> = _isProtected

    fun toggleProtection() {
        viewModelScope.launch {
            if (!_isProtected.value) {
                val identity = repository.generateRandomGhostIdentity()
                val newImei = IdentitySimulator.generateValidIMEI()
                val newMac = IdentitySimulator.generateRandomMAC()
                val location = IdentitySimulator.getRandomGlobalLocation()

                prefs.saveSpoofedIdentity(
                    imei = newImei,
                    mac = newMac,
                    city = location.cityName,
                    lat = location.latitude,
                    lng = location.longitude
                )
                
                _isProtected.value = true
            } else {
                prefs.clearIdentity()
                _isProtected.value = false
            }
        }
    }
}
