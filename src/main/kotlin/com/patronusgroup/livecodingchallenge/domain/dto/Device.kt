package com.patronusgroup.livecodingchallenge.domain.dto

import java.util.*

data class Device(
    val uuid: UUID = UUID.randomUUID(),
    val serialNumber: String,
    val phoneNumber: String,
    val model: String
)
