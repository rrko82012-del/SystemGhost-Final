package com.example.systemghost.data.repository

import com.example.systemghost.data.database.DeviceProfile
import com.example.systemghost.data.database.SystemGhostDao
import kotlin.random.Random

data class GhostIdentity(
    val deviceProfile: DeviceProfile,
    val spoofedIp: String,
    val country: String
)

class IdentityRepository(private val dao: SystemGhostDao) {

    suspend fun generateRandomGhostIdentity(): GhostIdentity? {
        val randomDevice = dao.getRandomDeviceProfile()
        val randomIpPool = dao.getRandomIpPool()

        if (randomDevice == null || randomIpPool == null) {
            return null
        }

        val finalOctet = Random.nextInt(1, randomIpPool.subnetRange)
        val fullSpoofedIp = "${randomIpPool.baseIp}$finalOctet"

        return GhostIdentity(
            deviceProfile = randomDevice,
            spoofedIp = fullSpoofedIp,
            country = randomIpPool.countryName
        )
    }
}
