package com.example.systemghost.xposed

import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import android.net.Uri
import android.content.ContentResolver

class SystemGhostModule : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: LoadPackageParam) {
        if (lpparam.packageName == "android") return

        val contentResolver: ContentResolver = XposedHelpers.callMethod(XposedHelpers.callStaticMethod(XposedHelpers.findClass("android.app.AppGlobals", null), "getInitialApplication"), "getContentResolver") as ContentResolver
        val cursor = contentResolver.query(
            Uri.parse("content://com.example.systemghost.provider/spoofed_identity"),
            null, null, null, null
        )

        var isProtected = false
        var spoofedImei: String? = null
        var spoofedMac: String? = null

        cursor?.use { c ->
            if (c.moveToFirst()) {
                val imeiIndex = c.getColumnIndex("imei")
                val macIndex = c.getColumnIndex("mac")
                val isProtectedIndex = c.getColumnIndex("is_protected")

                if (imeiIndex != -1) spoofedImei = c.getString(imeiIndex)
                if (macIndex != -1) spoofedMac = c.getString(macIndex)
                if (isProtectedIndex != -1) isProtected = c.getInt(isProtectedIndex) == 1
            }
        }

        if (!isProtected) return

        if (spoofedImei != null) {
            try {
                XposedHelpers.findAndHookMethod(
                    "android.telephony.TelephonyManager",
                    lpparam.classLoader,
                    "getDeviceId",
                    object : XC_MethodHook() {
                        override fun beforeHookedMethod(param: MethodHookParam) {
                            param.result = spoofedImei
                        }
                    }
                )
                XposedHelpers.findAndHookMethod(
                    "android.telephony.TelephonyManager",
                    lpparam.classLoader,
                    "getImei",
                    object : XC_MethodHook() {
                        override fun beforeHookedMethod(param: MethodHookParam) {
                            param.result = spoofedImei
                        }
                    }
                )
            } catch (e: Throwable) {
                XposedBridge.log("SystemGhost Error: Failed to hook TelephonyManager - ${e.message}")
            }
        }

        if (spoofedMac != null) {
            try {
                XposedHelpers.findAndHookMethod(
                    "android.net.wifi.WifiInfo",
                    lpparam.classLoader,
                    "getMacAddress",
                    object : XC_MethodHook() {
                        override fun beforeHookedMethod(param: MethodHookParam) {
                            param.result = spoofedMac
                        }
                    }
                )
                XposedHelpers.findAndHookMethod(
                    "java.net.NetworkInterface",
                    lpparam.classLoader,
                    "getHardwareAddress",
                    object : XC_MethodHook() {
                        override fun afterHookedMethod(param: MethodHookParam) {
                            val macBytes = spoofedMac!!.split(":").map { it.toInt(16).toByte() }.toByteArray()
                            param.result = macBytes
                        }
                    }
                )
            } catch (e: Throwable) {
                XposedBridge.log("SystemGhost Error: Failed to hook WifiInfo/NetworkInterface - ${e.message}")
            }
        }
    }
}
