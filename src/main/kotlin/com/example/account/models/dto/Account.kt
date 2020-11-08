package com.example.account.models.dto

import java.time.LocalDateTime

data class Account(val iban: String, val createTimestamp: LocalDateTime, val locked: Boolean)