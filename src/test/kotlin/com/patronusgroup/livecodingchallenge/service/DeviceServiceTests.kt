package com.patronusgroup.livecodingchallenge.service

import com.patronusgroup.livecodingchallenge.persistence.dao.DeviceDao
import com.patronusgroup.livecodingchallenge.persistence.repository.DeviceRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.util.*

class DeviceServiceTests {

    @Mock
    private lateinit var deviceRepository: DeviceRepository

    private lateinit var deviceService: DeviceService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        deviceService = DeviceService(
            deviceRepository
        )
    }

    private val deviceDao = DeviceDao(
        uuid = UUID.randomUUID(),
        serialNumber = "Test",
        phoneNumber = "43132142131",
        model = "Base"
    )

    @Test
    fun `test findByUuid`() {

        val deviceDto = deviceDao.toDomain()

        whenever(deviceRepository.findByUuid(deviceDto.uuid)).thenReturn(deviceDao)

        val result = deviceService.findByUuid(deviceDto.uuid)

        TestCase.assertEquals(deviceDto, result)
    }

    @Test
    fun `test createDevice`() {

        val deviceDto = deviceDao.toDomain()

        whenever(deviceRepository.save(deviceDao)).thenReturn(deviceDao)

        val result = deviceService.createDevice(deviceDto)

        TestCase.assertEquals(deviceDto, result)
    }

    @Test
    fun `test findAll`() {

        val devices = listOf(deviceDao)

        whenever(deviceRepository.findAll()).thenReturn(devices)

        val result = deviceService.findAll()

        TestCase.assertEquals(devices.map { it.toDomain() }, result)
    }

    @Test
    fun `test findBySerialNumber`() {

        val deviceDto = deviceDao.toDomain()

        whenever(deviceRepository.findBySerialNumber(deviceDto.serialNumber)).thenReturn(deviceDao)

        val result = deviceService.findBySerialNumber(deviceDto.serialNumber)

        TestCase.assertEquals(deviceDto, result)
    }
}