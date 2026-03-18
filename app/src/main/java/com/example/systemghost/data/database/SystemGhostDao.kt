package com.example.systemghost.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SystemGhostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeviceProfiles(profiles: List<DeviceProfile>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIpPools(ipPools: List<IpPool>)

    @Query("SELECT * FROM device_profiles ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomDeviceProfile(): DeviceProfile?

    @Query("SELECT * FROM ip_pool ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomIpPool(): IpPool?
    
    @Query("SELECT * FROM ip_pool WHERE country_code = :countryCode ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomIpPoolByCountry(countryCode: String): IpPool?
}
