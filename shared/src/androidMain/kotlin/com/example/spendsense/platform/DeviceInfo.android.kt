package com.example.spendsense.platform

import android.content.res.Resources
import android.os.Build
import androidx.annotation.RequiresApi
import kotlin.math.roundToInt

actual class DeviceInfo actual constructor() {

    private val displayMetrics = Resources.getSystem().displayMetrics

    actual val osName: String = "Android"
    actual val osVersion: String = "${Build.VERSION.SDK_INT}"
    @RequiresApi(Build.VERSION_CODES.DONUT)
    actual val model: String = "${Build.MANUFACTURER} ${Build.MODEL}"
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    actual val cpu: String = Build.SUPPORTED_ABIS.firstOrNull() ?: "Unknown cpu"
    actual val screenWidth: Int = displayMetrics.widthPixels
    actual val screenHeight: Int = displayMetrics.heightPixels
    actual val screenDensity: Int = displayMetrics.density.roundToInt()

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    actual fun getSummary(): String =
        "osName: $osName\n" +
                "osVersion: $osVersion\n" +
                "model: $model\n" +
                "cpu: $cpu\n" +
                "screenWidth: $screenWidth\n" +
                "screenHeight: $screenHeight\n" +
                "screenDensity: $screenDensity\n"

}