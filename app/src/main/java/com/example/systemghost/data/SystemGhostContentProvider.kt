package com.example.systemghost.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri

class SystemGhostContentProvider : ContentProvider() {

    private lateinit var preferencesManager: PreferencesManager

    companion object {
        const val AUTHORITY = "com.example.systemghost.provider"
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/spoofed_identity")

        private const val SPOOFED_IDENTITY = 1
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

        init {
            uriMatcher.addURI(AUTHORITY, "spoofed_identity", SPOOFED_IDENTITY)
        }
    }

    override fun onCreate(): Boolean {
        context?.let {
            preferencesManager = PreferencesManager(it)
            return true
        }
        return false
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return when (uriMatcher.match(uri)) {
            SPOOFED_IDENTITY -> {
                val cursor = MatrixCursor(arrayOf("imei", "mac", "city", "lat", "lng", "is_protected"))
                val imei = preferencesManager.getSpoofedIMEI()
                val mac = preferencesManager.getSpoofedMAC()
                val city = preferencesManager.getSpoofedCity()
                val lat = preferencesManager.getSpoofedLat()
                val lng = preferencesManager.getSpoofedLng()
                val isProtected = preferencesManager.isProtected()

                cursor.addRow(arrayOf(imei, mac, city, lat, lng, isProtected))
                cursor
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            SPOOFED_IDENTITY -> "vnd.android.cursor.item/vnd.com.example.systemghost.provider.spoofed_identity"
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("Insert not supported")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("Delete not supported")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw UnsupportedOperationException("Update not supported")
    }
}
