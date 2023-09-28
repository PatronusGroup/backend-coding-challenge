package com.patronusgroup.livecodingchallenge.usecase_impl.mappers

import com.patronusgroup.livecodingchallenge.domain.dto.Device
import com.patronusgroup.livecodingchallenge.domain.dto.Holder
import com.patronusgroup.livecodingchallenge.usecase_api.ICreateDevice
import com.patronusgroup.livecodingchallenge.usecase_api.ICreateHolder

internal fun ICreateDevice.Request.toDomain(): Device {
    return Device(
        serialNumber = serialNumber,
        phoneNumber = phoneNumber,
        model = model
    )
}

internal fun ICreateHolder.Request.toDomain(): Holder {
    return Holder(
        firstName = firstName,
        lastName = lastName,
        address = address,
        birthday = birthday
    )
}