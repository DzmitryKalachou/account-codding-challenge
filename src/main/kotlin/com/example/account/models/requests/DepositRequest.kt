package com.example.account.models.requests

import javax.validation.constraints.Min

data class DepositRequest(@get:Min(1) val amount: Long)