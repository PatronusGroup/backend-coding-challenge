package com.patronusgroup.livecodingchallenge.usecase_api

import java.util.*

interface ICreateDevice {

    data class Request(
        val serialNumber: String,
        val phoneNumber: String,
        val model: String
    )

    operator fun invoke(request: Request): UUID
}