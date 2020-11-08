package com.example.account.services.accounts

import com.example.account.errors.ApplicationException
import com.example.account.models.dto.Transaction
import com.example.account.models.dto.extensions.toDto
import com.example.account.models.responses.BalanceResponse
import com.example.account.repositories.AccountRepository
import com.example.account.repositories.TransactionRepository
import org.springframework.stereotype.Service

@Service
class AccountInformationService(private val accountRepository: AccountRepository,
                                private val accountBalanceCalculatorComponent: AccountBalanceCalculatorComponent,
                                private val transactionRepository: TransactionRepository) {

    fun getIbanBalance(iban: String): BalanceResponse {
        if (!accountRepository.isIbanExists(iban)) {
            throw ApplicationException.AccountNotExists(iban)
        }
        return BalanceResponse(accountBalanceCalculatorComponent.calculateBalance(iban, transactionRepository.getAccountTransactions(iban)))
    }

    fun getIbanTransactions(iban: String): List<Transaction> {
        return transactionRepository.getAccountTransactions(iban).map { it.toDto() }
    }
}