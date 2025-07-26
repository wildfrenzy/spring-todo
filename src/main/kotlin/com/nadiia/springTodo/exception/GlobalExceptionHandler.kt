package com.nadiia.springTodo.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

data class ApiError(val status: Int, val message: String, val timestamp: Long = System.currentTimeMillis())

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException::class)
    fun notFound(ex: NoSuchElementException): ResponseEntity<ApiError> {
        val error = ApiError(HttpStatus.NOT_FOUND.value(), ex.message ?: "Not Found")
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(ex: IllegalArgumentException): ResponseEntity<ApiError> {
        val error = ApiError(HttpStatus.BAD_REQUEST.value(), ex.message ?: "Bad Request")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error)
    }
}