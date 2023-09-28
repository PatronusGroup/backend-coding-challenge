package com.patronusgroup.livecodingchallenge.domain.gateway

import com.patronusgroup.livecodingchallenge.domain.dto.Device
import java.util.*

interface IDeviceService {

    fun createDevice(device: Device): Device

    fun findByUuid(uuid: UUID): Device?

    fun findAll(): List<Device>

    fun findBySerialNumber(serialNumber: String): Device?
}