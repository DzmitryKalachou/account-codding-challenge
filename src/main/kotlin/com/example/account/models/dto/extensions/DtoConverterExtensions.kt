package com.example.account.models.dto.extensions

import com.example.account.models.dto.Account
import com.example.account.models.dto.Transaction
import com.example.db.tables.records.AccountRecord
import com.example.db.tables.records.TransactionRecord

fun TransactionRecord.toDto(): Transaction {
    return Transaction(id, createTimestamp.toLocalDateTime(), senderIban, receiverIban, amount)
}

fun AccountRecord.toDto(): Account {
    return Account(iban, createTimestamp.toLocalDateTime(), locked)
}