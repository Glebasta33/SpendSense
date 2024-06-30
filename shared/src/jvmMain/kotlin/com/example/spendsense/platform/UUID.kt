package com.example.spendsense.platform

import java.util.UUID

actual fun randomUUID() = UUID.randomUUID().toString()