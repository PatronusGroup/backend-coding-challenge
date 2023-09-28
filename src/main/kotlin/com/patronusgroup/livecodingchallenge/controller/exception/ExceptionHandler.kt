package com.patronusgroup.livecodingchallenge.controller.exception

import com.patronusgroup.livecodingchallenge.domain.exception.EntitySaveException
import com.patronusgroup.livecodingchallenge.domain.exception.ErrorDetails
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class ExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(EntitySaveException::class)])
    fun handleUnsuccessfullySaveException(ex: EntitySaveException, request: WebRequest): ResponseEntity<Any> {
        return ResponseEntity(ErrorDetails(ex.message), HttpStatus.CONFLICT)
    }
}