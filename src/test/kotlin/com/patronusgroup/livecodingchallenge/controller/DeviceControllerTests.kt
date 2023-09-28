package com.patronusgroup.livecodingchallenge.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.patronusgroup.livecodingchallenge.domain.dto.Device
import com.patronusgroup.livecodingchallenge.usecase_api.ICreateDevice
import com.patronusgroup.livecodingchallenge.usecase_api.IGetAllDevices
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.util.*

@ContextConfiguration
@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
class DeviceControllerTests {

    private lateinit var mvc: MockMvc

    @MockBean
    lateinit var createDevice: ICreateDevice

    @MockBean
    lateinit var getAllDevices: IGetAllDevices

    @Before
    fun setUp() {
        mvc = MockMvcBuilders.standaloneSetup(
            DeviceController(
                createDevice,
                getAllDevices
            )
        ).build()
    }

    @Test
    fun test_createDevice_returnsDevice() {

        val createdDevice = UUID.randomUUID()

        whenever(createDevice.invoke(any())).thenReturn(createdDevice)

        val result = this.mvc
            .perform(
                MockMvcRequestBuilders
                    .post("/device")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(ObjectMapper().writeValueAsBytes(createDeviceRequest))
            )
            .andExpect(MockMvcResultMatchers.status().isOk) // Assert HTTP status code
            .andReturn()
            .response

        assertNotNull(result)
        assertNotNull(result.contentAsString)
        assertEquals(
            ObjectMapper().writeValueAsString(createdDevice),
            result.contentAsString
        )

        // You can also check the content type here if needed
        assertEquals(
            MediaType.APPLICATION_JSON_VALUE,
            result.contentType
        )
    }


    @Test
    fun test_createDevice_wrongRequest_throwsError() {

        val createdDevice = UUID.randomUUID()

        whenever(createDevice.invoke(any())).thenReturn(createdDevice)

        this.mvc
            .perform(
                MockMvcRequestBuilders
                    .post("/device")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content("")
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()
    }

    @Test
    fun test_getDeviceBySerialNumber_returnsDevice() {

        whenever(getAllDevices.invoke()).thenReturn(
            IGetAllDevices.Response(devices)
        )

        val result: String = this.mvc
            .perform(
                MockMvcRequestBuilders
                    .get("/device")
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
            .response
            .contentAsString

        assertNotNull(result)
        assertEquals(
            ObjectMapper().writeValueAsString(
                IGetAllDevices.Response(devices)
            ),
            result
        )
    }

    private val createDeviceRequest = ICreateDevice.Request(
        serialNumber = "RFATEST",
        phoneNumber = "2314312321",
        model = "Base"
    )

    private val devices = listOf(
        Device(
            uuid = UUID.randomUUID(),
            serialNumber = "Device1",
            phoneNumber = "1234567890",
            model = "Model1"
        ),
        Device(
            uuid = UUID.randomUUID(),
            serialNumber = "Device2",
            phoneNumber = "9876543210",
            model = "Model2"
        )
    )
}
