package com.patronusgroup.livecodingchallenge.domain.gateway

import com.patronusgroup.livecodingchallenge.domain.dto.Holder
import java.util.*

interface IHolderService {

    fun createHolder(holder: Holder): Holder

    fun findByUuid(uuid: UUID): Holder?

    fun findAll(): List<Holder>
}