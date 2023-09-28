package com.patronusgroup.livecodingchallenge.usecase

import com.patronusgroup.livecodingchallenge.domain.dto.Holder
import com.patronusgroup.livecodingchallenge.domain.exception.EntitySaveException
import com.patronusgroup.livecodingchallenge.domain.gateway.IHolderService
import com.patronusgroup.livecodingchallenge.usecase_api.ICreateHolder
import com.patronusgroup.livecodingchallenge.usecase_impl.CreateHolder
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
import java.time.Instant
import java.util.*

class CreateHolderTests {

    @Mock
    private lateinit var holderService: IHolderService

    private lateinit var createHolderUseCase: ICreateHolder

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        createHolderUseCase = CreateHolder(holderService)
    }

    @Test
    fun `test invoke when holder is created successfully`() {

        whenever(holderService.createHolder(any())).doReturn(holder)

        val result = createHolderUseCase.invoke(request)

        assertEquals(holderUuid, result)

        verify(holderService).createHolder(any())
    }

    @Test
    fun `test invoke when holder creation fails`() {

        whenever(holderService.createHolder(any())).thenThrow(RuntimeException("Failed to create holder"))

        assertThrows<EntitySaveException> {
            createHolderUseCase.invoke(request)
        }

        verify(holderService).createHolder(any())
    }

    private val request = ICreateHolder.Request(
        firstName = "John",
        lastName="Doe",
        address = "Example Address",
        birthday = Date.from(Instant.now())
    )

    private val holderUuid = UUID.randomUUID()

    private val holder = Holder(
        uuid = holderUuid,
        firstName = "John",
        lastName = "Doe",
        address = "Example address",
        birthday = Date.from(Instant.now())
    )
}
