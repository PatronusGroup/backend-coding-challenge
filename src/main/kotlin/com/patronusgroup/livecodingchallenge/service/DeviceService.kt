package com.patronusgroup.livecodingchallenge.service

import com.patronusgroup.livecodingchallenge.domain.dto.Device
import com.patronusgroup.livecodingchallenge.domain.gateway.IDeviceService
import com.patronusgroup.livecodingchallenge.domain.mappers.toDao
import com.patronusgroup.livecodingchallenge.persistence.repository.DeviceRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class DeviceService(
    private val deviceRepository: DeviceRepository
): IDeviceService {

    override fun createDevice(device: Device): Device {
        return deviceRepository.save(device.toDao()).toDomain()
    }

    override fun findByUuid(uuid: UUID): Device? {
        return deviceRepository.findByUuid(uuid)?.toDomain()
    }

    override fun findAll(): List<Device> {
        return deviceRepository.findAll().map { it.toDomain() }
    }

    override fun findBySerialNumber(serialNumber: String): Device? {
        return deviceRepository.findBySerialNumber(serialNumber)?.toDomain()
    }
}