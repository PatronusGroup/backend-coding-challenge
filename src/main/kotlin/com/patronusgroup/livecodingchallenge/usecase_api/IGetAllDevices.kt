package com.patronusgroup.livecodingchallenge.usecase_api

import com.patronusgroup.livecodingchallenge.domain.dto.Device


interface IGetAllDevices {

    data class Response(
        val devices: List<Device>
    )

    operator fun invoke(): Response
}