package com.example.systemghost.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "device_profiles")
data class DeviceProfile(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "brand") val brand: String,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "screen_resolution") val screenResolution: String,
    @ColumnInfo(name = "hardware_id") val hardwareId: String
)

@Entity(tableName = "ip_pool")
data class IpPool(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "country_code") val countryCode: String,
    @ColumnInfo(name = "country_name") val countryName: String,
    @ColumnInfo(name = "base_ip") val baseIp: String,
    @ColumnInfo(name = "subnet_range") val subnetRange: Int
)
