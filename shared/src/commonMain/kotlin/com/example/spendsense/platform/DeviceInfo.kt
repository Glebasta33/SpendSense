package com.example.spendsense.platform

expect class DeviceInfo() {
    val osName: String
    val osVersion: String
    val model: String
    val cpu: String
    val screenWidth: Int
    val screenHeight: Int
    val screenDensity: Int

    fun getSummary(): String
}