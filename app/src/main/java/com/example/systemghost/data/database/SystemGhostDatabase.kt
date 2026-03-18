package com.example.systemghost.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [DeviceProfile::class, IpPool::class], version = 1, exportSchema = false)
abstract class SystemGhostDatabase : RoomDatabase() {
    abstract fun systemGhostDao(): SystemGhostDao

    companion object {
        @Volatile
        private var INSTANCE: SystemGhostDatabase? = null

        fun getDatabase(context: Context): SystemGhostDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SystemGhostDatabase::class.java,
                    "system_ghost_db"
                )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            val dao = getDatabase(context).systemGhostDao()
                            dao.insertDeviceProfiles(DatabaseSeeder.getPredefinedDevices())
                            dao.insertIpPools(DatabaseSeeder.getPredefinedIpPools())
                        }
                    }
                })
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
