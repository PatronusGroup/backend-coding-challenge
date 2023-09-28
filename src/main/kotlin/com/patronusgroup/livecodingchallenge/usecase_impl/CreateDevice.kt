package com.patronusgroup.livecodingchallenge.usecase_impl

import com.patronusgroup.livecodingchallenge.domain.exception.EntitySaveException
import com.patronusgroup.livecodingchallenge.service.DeviceService
import com.patronusgroup.livecodingchallenge.usecase_api.ICreateDevice
import com.patronusgroup.livecodingchallenge.usecase_impl.mappers.toDomain
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreateDevice(
    private val deviceService: DeviceService
): ICreateDevice {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun invoke(request: ICreateDevice.Request): UUID {

        return try {

            deviceService.findBySerialNumber(request.serialNumber)?.let {
                throw EntitySaveException("Device with serial number: ${request.serialNumber } already exist.")
            }

            deviceService.createDevice(request.toDomain()).uuid
        } catch (ex: Exception) {
            logger.error(ex.message)
            throw EntitySaveException(ex.message ?: "Device  $request could not be created.")
        }
    }
}