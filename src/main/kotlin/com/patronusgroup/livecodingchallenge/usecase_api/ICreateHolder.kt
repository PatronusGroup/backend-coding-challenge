package com.patronusgroup.livecodingchallenge.usecase_api

import java.util.*

interface ICreateHolder {

    data class Request(
        val firstName: String,
        val lastName: String,
        val address: String,
        val birthday: Date
    )

    operator fun invoke(request: Request): UUID
}