package com.patronusgroup.livecodingchallenge.usecase

import com.patronusgroup.livecodingchallenge.domain.dto.Device
import com.patronusgroup.livecodingchallenge.domain.exception.EntityNotFoundException
import com.patronusgroup.livecodingchallenge.domain.gateway.IDeviceService
import com.patronusgroup.livecodingchallenge.usecase_api.IGetAllDevices
import com.patronusgroup.livecodingchallenge.usecase_impl.GetAllDevices
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetAllDevicesTests {

    @Mock
    private lateinit var deviceService: IDeviceService

    private lateinit var getAllDevicesUseCase: IGetAllDevices

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAllDevicesUseCase = GetAllDevices(deviceService)
    }

    @Test
    fun `test invoke when devices are found successfully`() {

        val devices = listOf(
            Device(
                uuid = java.util.UUID.randomUUID(),
                serialNumber = "Device1",
                phoneNumber = "1234567890",
                model = "Model1"
            ),
            Device(
                uuid = java.util.UUID.randomUUID(),
                serialNumber = "Device2",
                phoneNumber = "9876543210",
                model = "Model2"
            )
        )

        whenever(deviceService.findAll()).doReturn(devices)

        val result = getAllDevicesUseCase.invoke()

        assertEquals(devices, result.devices)

        verify(deviceService).findAll()
    }

    @Test
    fun `test invoke when device retrieval fails`() {

        whenever(deviceService.findAll()).thenThrow(RuntimeException("Failed to retrieve devices"))

        assertThrows<EntityNotFoundException> {
            getAllDevicesUseCase.invoke()
        }

        verify(deviceService).findAll()
    }
}
