package com.patronusgroup.livecodingchallenge.usecase

import com.patronusgroup.livecodingchallenge.domain.dto.Holder
import com.patronusgroup.livecodingchallenge.domain.exception.EntityNotFoundException
import com.patronusgroup.livecodingchallenge.domain.gateway.IHolderService
import com.patronusgroup.livecodingchallenge.usecase_api.IGetAllHolders
import com.patronusgroup.livecodingchallenge.usecase_impl.GetAllHolders
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.time.Instant
import java.util.*

class GetAllHoldersTests {

    @Mock
    private lateinit var holderService: IHolderService

    private lateinit var getAllHoldersUseCase: IGetAllHolders

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getAllHoldersUseCase = GetAllHolders(holderService)
    }

    @Test
    fun `test invoke when holders are found successfully`() {

        val holders = listOf(
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

        whenever(holderService.findAll()).doReturn(holders)

        val result = getAllHoldersUseCase.invoke()

        assertEquals(holders, result.holders)

        verify(holderService).findAll()
    }

    @Test
    fun `test invoke when holder retrieval fails`() {

        whenever(holderService.findAll()).thenThrow(RuntimeException("Failed to retrieve holders"))

        assertThrows<EntityNotFoundException> {
            getAllHoldersUseCase.invoke()
        }

        verify(holderService).findAll()
    }
}
