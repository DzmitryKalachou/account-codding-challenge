package com.example.account.errors

import com.example.account.models.responses.error.AppFieldError
import com.example.account.models.responses.error.ErrorResponse
import com.example.account.models.responses.error.ValidationErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandler {

    private val logger = LoggerFactory.getLogger(ErrorHandler::class.java)


    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        logger.error(e.message, e)
        val (response, status) = if (e is ApplicationException) {
            ErrorResponse(e.message, e.errorCode) to e.statusCode
        } else {
            ErrorResponse(e.message ?: "Internal server error", ErrorCode.INTERNAL_SERVER_ERROR) to HttpStatus.INTERNAL_SERVER_ERROR
        }
        return ResponseEntity(response, status)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<Any> {
        logger.info(e.message, e)
        val errors = e.bindingResult.allErrors.map {
            if (it is FieldError) {
                AppFieldError(it.field, it.defaultMessage ?: "")
            } else {
                AppFieldError("request", it.defaultMessage ?: "")
            }
        }
        val message = errors.joinToString(", ") { "${it.field} ${it.message}" }
        return ResponseEntity(ValidationErrorResponse(message, errors), HttpStatus.BAD_REQUEST)
    }

}
