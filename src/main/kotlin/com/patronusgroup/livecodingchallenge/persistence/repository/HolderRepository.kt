package com.patronusgroup.livecodingchallenge.persistence.repository

import com.patronusgroup.livecodingchallenge.persistence.dao.HolderDao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface HolderRepository: CrudRepository<HolderDao, UUID> {
    fun findByUuid(uuid: UUID): HolderDao?
}