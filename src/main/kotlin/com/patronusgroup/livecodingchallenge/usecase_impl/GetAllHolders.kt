package com.patronusgroup.livecodingchallenge.usecase_impl

import com.patronusgroup.livecodingchallenge.domain.exception.EntityNotFoundException
import com.patronusgroup.livecodingchallenge.domain.gateway.IHolderService
import com.patronusgroup.livecodingchallenge.usecase_api.IGetAllHolders
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GetAllHolders(
    private val holderService: IHolderService
): IGetAllHolders {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun invoke(): IGetAllHolders.Response {
        return try {
            IGetAllHolders.Response(holderService.findAll())
        } catch (ex: Exception) {
            logger.error(ex.message)
            throw EntityNotFoundException(ex.message)
        }
    }
}