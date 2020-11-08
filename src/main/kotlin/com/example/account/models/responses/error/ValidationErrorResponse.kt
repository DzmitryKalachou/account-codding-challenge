package com.example.account.models.responses.error

data class ValidationErrorResponse(val message: String,
                                   val fields: List<AppFieldError>)


data class AppFieldError(val field: String?, val message: String)
