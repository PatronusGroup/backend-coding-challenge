package com.patronusgroup.livecodingchallenge.controller

import com.patronusgroup.livecodingchallenge.usecase_api.ICreateDevice
import com.patronusgroup.livecodingchallenge.usecase_api.IGetAllDevices
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/device")
class DeviceController(
    private val createDevice: ICreateDevice,
    private val getAllDevices: IGetAllDevices
) {

    @CrossOrigin
    @PostMapping
    fun createDevice(@RequestBody request: ICreateDevice.Request): UUID {
        return createDevice.invoke(request)
    }

    @CrossOrigin
    @GetMapping
    fun findAll(): IGetAllDevices.Response {
        return getAllDevices.invoke()
    }
}