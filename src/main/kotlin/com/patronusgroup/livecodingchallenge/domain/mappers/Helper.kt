package com.patronusgroup.livecodingchallenge.domain.mappers

import com.patronusgroup.livecodingchallenge.domain.dto.Device
import com.patronusgroup.livecodingchallenge.domain.dto.Holder
import com.patronusgroup.livecodingchallenge.persistence.dao.DeviceDao
import com.patronusgroup.livecodingchallenge.persistence.dao.HolderDao

internal fun Holder.toDao(): HolderDao = HolderDao(
    uuid = uuid,
    firstName = firstName,
    lastName = lastName,
    address = address,
    birthday = birthday
)

internal fun Device.toDao(): DeviceDao = DeviceDao(
    uuid = uuid,
    serialNumber = serialNumber,
    phoneNumber = phoneNumber,
    model = model
)