package com.patronusgroup.livecodingchallenge.usecase_impl

import com.patronusgroup.livecodingchallenge.domain.exception.EntitySaveException
import com.patronusgroup.livecodingchallenge.domain.gateway.IHolderService
import com.patronusgroup.livecodingchallenge.usecase_api.ICreateHolder
import com.patronusgroup.livecodingchallenge.usecase_impl.mappers.toDomain
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.util.*

@Component
class CreateHolder(
    private val holderService: IHolderService
): ICreateHolder {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun invoke(request: ICreateHolder.Request): UUID {
        return try {
            holderService.createHolder(request.toDomain()).uuid
        } catch (ex: Exception) {
            logger.error(ex.message)
            throw EntitySaveException(ex.message ?: "Holder $request could not be created.")
        }
    }
}