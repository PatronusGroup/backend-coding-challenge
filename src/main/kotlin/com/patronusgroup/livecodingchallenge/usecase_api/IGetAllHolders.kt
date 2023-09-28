package com.patronusgroup.livecodingchallenge.usecase_api

import com.patronusgroup.livecodingchallenge.domain.dto.Holder

interface IGetAllHolders {

    data class Response(
        val holders: List<Holder>
    )

    operator fun invoke(): Response
}