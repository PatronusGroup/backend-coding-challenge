package com.patronusgroup.livecodingchallenge.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.patronusgroup.livecodingchallenge.domain.dto.Holder
import com.patronusgroup.livecodingchallenge.usecase_api.ICreateHolder
import com.patronusgroup.livecodingchallenge.usecase_api.IGetAllHolders
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
import java.time.Instant
import java.util.*

@ContextConfiguration
@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
class HolderControllerTests {

    private lateinit var mvc: MockMvc

    @MockBean
    lateinit var createHolder: ICreateHolder

    @MockBean
    lateinit var getAllHolders: IGetAllHolders

    @Before
    fun setUp() {
        mvc = MockMvcBuilders.standaloneSetup(
            HolderController(
                createHolder,
                getAllHolders
            )
        ).build()
    }

    private val createHolderRequest = ICreateHolder.Request(
        firstName = "John",
        lastName = "Doe",
        address = "Example address",
        birthday = Date.from(Instant.now())
    )

    private val holders = listOf(
        Holder(
            uuid = UUID.randomUUID(),
            firstName = "John",
            lastName = "Doe",
            address = "Example address",
            birthday = Date.from(Instant.now())
        ),
        Holder(
            uuid = UUID.randomUUID(),
            firstName = "Test",
            lastName = "User",
            address = "Example address 2",
            birthday = Date.from(Instant.now())
        )
    )


    @Test
    fun test_createHolder_returnsHolder() {

        val createdHolder = UUID.randomUUID()

        whenever(createHolder.invoke(any())).thenReturn(createdHolder)

        val result: String = this.mvc
            .perform(
                MockMvcRequestBuilders
                    .post("/holder")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(ObjectMapper().writeValueAsString(createHolderRequest))
            )
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andReturn()
            .response
            .contentAsString

        assertNotNull(result)
        assertEquals(
            ObjectMapper().writeValueAsString(createdHolder),
            result
        )
    }

    @Test
    fun test_createHolder_wrongRequest_throwsError() {

        val createdHolder = UUID.randomUUID()

        whenever(createHolder.invoke(any())).thenReturn(createdHolder)

        this.mvc
            .perform(
                MockMvcRequestBuilders
                    .post("/holder")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content("")
            )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()
    }

    @Test
    fun test_getAllHolders_returnsHolders() {

        whenever(getAllHolders.invoke()).thenReturn(
            IGetAllHolders.Response(holders)
        )

        val result: String = this.mvc
            .perform(
                MockMvcRequestBuilders
                    .get("/holder")
            )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
            .response
            .contentAsString

        assertNotNull(result)
        assertEquals(
            ObjectMapper().writeValueAsString(
                IGetAllHolders.Response(holders)
            ),
            result
        )
    }
}
