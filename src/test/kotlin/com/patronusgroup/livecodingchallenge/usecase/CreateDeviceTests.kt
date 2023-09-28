package com.patronusgroup.livecodingchallenge.usecase

import com.patronusgroup.livecodingchallenge.domain.dto.Device
import com.patronusgroup.livecodingchallenge.domain.exception.EntitySaveException
import com.patronusgroup.livecodingchallenge.service.DeviceService
import com.patronusgroup.livecodingchallenge.usecase_api.ICreateDevice
import com.patronusgroup.livecodingchallenge.usecase_impl.CreateDevice
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.util.*

class CreateDeviceTests {

    @Mock
    private lateinit var deviceService: DeviceService

    private lateinit var createDeviceUseCase: ICreateDevice

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        createDeviceUseCase = CreateDevice(deviceService)
    }

    @Test
    fun `test invoke when device does not exist`() {

        whenever(deviceService.findBySerialNumber(request.serialNumber)).doReturn(null)

        whenever(deviceService.createDevice(any())).doReturn(device)

        val result = createDeviceUseCase.invoke(request)

        assertEquals(deviceUuid, result)

        verify(deviceService).findBySerialNumber(request.serialNumber)
        verify(deviceService).createDevice(any())
    }

    @Test
    fun `test invoke when device already exists`() {

        whenever(deviceService.findBySerialNumber(request.serialNumber)).doReturn(Device(
            uuid = UUID.randomUUID(),
            serialNumber = request.serialNumber,
            phoneNumber = request.phoneNumber,
            model = request.model
        ))

        assertThrows<EntitySaveException> {
            createDeviceUseCase.invoke(request)
        }

        verify(deviceService).findBySerialNumber(request.serialNumber)
    }

    private val deviceUuid = UUID.randomUUID()

    private val device = Device(
        uuid =deviceUuid,
        serialNumber = "Test",
        phoneNumber = "43132142131",
        model = "Base"
    )

    private val request = ICreateDevice.Request(
        serialNumber = "TestSerial",
        phoneNumber = "1234567890",
        model = "TestModel"
    )
}
