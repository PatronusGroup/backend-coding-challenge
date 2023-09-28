package com.patronusgroup.livecodingchallenge.persistence.repository

import com.patronusgroup.livecodingchallenge.persistence.dao.DeviceDao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DeviceRepository: CrudRepository<DeviceDao, UUID> {

    fun findByUuid(uuid: UUID): DeviceDao?

    fun findBySerialNumber(serialNumber: String): DeviceDao?
}