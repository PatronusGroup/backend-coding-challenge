package com.patronusgroup.livecodingchallenge.service

import com.patronusgroup.livecodingchallenge.domain.dto.Holder
import com.patronusgroup.livecodingchallenge.domain.gateway.IHolderService
import com.patronusgroup.livecodingchallenge.domain.mappers.toDao
import com.patronusgroup.livecodingchallenge.persistence.repository.HolderRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class HolderService(
    private val holderRepository: HolderRepository
): IHolderService {

    override fun createHolder(holder: Holder): Holder {
        return holderRepository.save(holder.toDao()).toDomain()
    }

    override fun findByUuid(uuid: UUID): Holder? {
        return holderRepository.findByUuid(uuid)?.toDomain()
    }

    override fun findAll(): List<Holder> {
        return holderRepository.findAll().map { it.toDomain() }
    }
}