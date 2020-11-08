package com.example.account.models.dto

import java.time.LocalDateTime

data class Transaction(val id: Long,
                       val createTimestamp: LocalDateTime,
                       val senderIban: String?,
                       val receiverIban: String,
                       val amount: Long)