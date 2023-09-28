package com.patronusgroup.livecodingchallenge.domain.dto

import java.util.*

data class Holder(
    val uuid: UUID = UUID.randomUUID(),
    val firstName: String,
    val lastName: String,
    val address: String,
    val birthday: Date
)
