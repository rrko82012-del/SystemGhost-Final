package com.example.systemghost.data

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("SystemGhost_Prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IMEI = "spoofed_imei"
        private const val KEY_MAC = "spoofed_mac"
        private const val KEY_LAT = "spoofed_lat"
        private const val KEY_LNG = "spoofed_lng"
        private const val KEY_CITY = "spoofed_city"
        private const val KEY_IS_PROTECTED = "is_protected"
    }

    fun saveSpoofedIdentity(imei: String, mac: String, city: String, lat: Double, lng: Double) {
        prefs.edit().apply {
            putString(KEY_IMEI, imei)
            putString(KEY_MAC, mac)
            putString(KEY_CITY, city)
            putFloat(KEY_LAT, lat.toFloat())
            putFloat(KEY_LNG, lng.toFloat())
            putBoolean(KEY_IS_PROTECTED, true)
            apply()
        }
    }

    fun getSpoofedIMEI(): String? = prefs.getString(KEY_IMEI, null)
    fun getSpoofedMAC(): String? = prefs.getString(KEY_MAC, null)
    fun getSpoofedCity(): String? = prefs.getString(KEY_CITY, null)
    fun getSpoofedLat(): Double = prefs.getFloat(KEY_LAT, 0.0f).toDouble()
    fun getSpoofedLng(): Double = prefs.getFloat(KEY_LNG, 0.0f).toDouble()
    fun isProtected(): Boolean = prefs.getBoolean(KEY_IS_PROTECTED, false)

    fun clearIdentity() {
        prefs.edit().clear().apply()
    }
}
