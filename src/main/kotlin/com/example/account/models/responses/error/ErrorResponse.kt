package com.example.account.models.responses.error

import com.example.account.errors.ErrorCode

data class ErrorResponse(val message: String, val code: ErrorCode)