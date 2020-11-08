package com.example.account.services.accounts

import com.example.db.tables.records.TransactionRecord
import org.springframework.stereotype.Component

@Component
class AccountBalanceCalculatorComponent {

    fun calculateBalance(iban: String, transactions: List<TransactionRecord>): Long {
        return transactions.map { if (it.senderIban == iban) it.amount * -1 else it.amount }.sum()
    }

}