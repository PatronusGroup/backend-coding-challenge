package com.patronusgroup.livecodingchallenge.usecase_impl

import com.patronusgroup.livecodingchallenge.domain.exception.EntityNotFoundException
import com.patronusgroup.livecodingchallenge.domain.gateway.IDeviceService
import com.patronusgroup.livecodingchallenge.usecase_api.IGetAllDevices
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GetAllDevices(
    private val deviceService: IDeviceService
): IGetAllDevices {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun invoke(): IGetAllDevices.Response {
        return try {
            IGetAllDevices.Response(deviceService.findAll())
        } catch (ex: Exception) {
            logger.error(ex.message)
            throw EntityNotFoundException(ex.message)
        }
    }
}