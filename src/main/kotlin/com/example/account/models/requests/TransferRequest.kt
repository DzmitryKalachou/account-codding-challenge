package com.example.account.models.requests

import javax.validation.constraints.Min

data class TransferRequest(@get:Min(1) val amount: Long)