package com.example.systemghost.data.database

object DatabaseSeeder {
    fun getPredefinedDevices(): List<DeviceProfile> {
        return listOf(
            DeviceProfile(brand = "Samsung", model = "Galaxy S24 Ultra", screenResolution = "1440x3120", hardwareId = "qcom"),
            DeviceProfile(brand = "Google", model = "Pixel 8 Pro", screenResolution = "1344x2992", hardwareId = "tensor"),
            DeviceProfile(brand = "Apple", model = "iPhone 15 Pro Max", screenResolution = "1290x2796", hardwareId = "a17"),
            DeviceProfile(brand = "Xiaomi", model = "14 Pro", screenResolution = "1440x3200", hardwareId = "snapdragon"),
            DeviceProfile(brand = "Huawei", model = "P60 Pro", screenResolution = "1220x2700", hardwareId = "kirin")
        )
    }

    fun getPredefinedIpPools(): List<IpPool> {
        return listOf(
            IpPool(countryCode = "US", countryName = "United States", baseIp = "104.28.14.", subnetRange = 254),
            IpPool(countryCode = "GB", countryName = "United Kingdom", baseIp = "178.62.19.", subnetRange = 254),
            IpPool(countryCode = "DE", countryName = "Germany", baseIp = "95.217.33.", subnetRange = 254),
            IpPool(countryCode = "JP", countryName = "Japan", baseIp = "103.156.12.", subnetRange = 254),
            IpPool(countryCode = "CH", countryName = "Switzerland", baseIp = "185.159.157.", subnetRange = 254)
        )
    }
}
