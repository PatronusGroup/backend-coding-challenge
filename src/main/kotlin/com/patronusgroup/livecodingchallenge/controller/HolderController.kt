package com.patronusgroup.livecodingchallenge.controller

import com.patronusgroup.livecodingchallenge.usecase_api.ICreateHolder
import com.patronusgroup.livecodingchallenge.usecase_api.IGetAllHolders
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/holder")
class HolderController(
    private val createHolder: ICreateHolder,
    private val getAllHolders: IGetAllHolders
) {

    @CrossOrigin
    @PostMapping
    fun createHolder(@RequestBody request: ICreateHolder.Request): UUID {
        return createHolder.invoke(request)
    }

    @CrossOrigin
    @GetMapping
    fun getAllHolders(): IGetAllHolders.Response {
        return getAllHolders.invoke()
    }
}