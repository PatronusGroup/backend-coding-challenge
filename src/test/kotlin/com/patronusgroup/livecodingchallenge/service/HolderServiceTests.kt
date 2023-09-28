package com.patronusgroup.livecodingchallenge.service

import com.patronusgroup.livecodingchallenge.persistence.dao.HolderDao
import com.patronusgroup.livecodingchallenge.persistence.repository.HolderRepository
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import java.time.Instant
import java.util.*

class HolderServiceTests {

    @Mock
    private lateinit var holderRepository: HolderRepository

    private lateinit var holderService: HolderService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        holderService = HolderService(holderRepository)
    }

    private val holderDao = HolderDao(
        uuid = UUID.randomUUID(),
        firstName = "John",
        lastName = "Doe",
        address = "Example address",
        birthday = Date.from(Instant.now())
    )

    @Test
    fun `test findByUuid`() {

        val holderDto = holderDao.toDomain()

        whenever(holderRepository.findByUuid(holderDto.uuid)).thenReturn(holderDao)

        val result = holderService.findByUuid(holderDto.uuid)

        TestCase.assertEquals(holderDto, result)
    }

    @Test
    fun `test createHolder`() {

        val holderDto = holderDao.toDomain()

        whenever(holderRepository.save(holderDao)).thenReturn(holderDao)

        val result = holderService.createHolder(holderDto)

        TestCase.assertEquals(holderDto, result)
    }

    @Test
    fun `test findAll`() {

        val holders = listOf(holderDao)

        whenever(holderRepository.findAll()).thenReturn(holders)

        val result = holderService.findAll()

        TestCase.assertEquals(holders.map { it.toDomain() }, result)
    }
}
