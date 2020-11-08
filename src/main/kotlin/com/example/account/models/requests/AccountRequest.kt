package com.example.account.models.requests

import com.example.account.validations.annotations.ValidIban
import com.example.db.enums.AccountType

data class AccountRequest(@get:ValidIban val iban: String, val type: AccountType)