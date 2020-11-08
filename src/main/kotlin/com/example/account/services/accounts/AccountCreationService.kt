package com.example.account.services.accounts

import com.example.account.errors.ApplicationException
import com.example.account.models.dto.Account
import com.example.account.models.dto.extensions.toDto
import com.example.account.models.requests.AccountRequest
import com.example.account.repositories.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountCreationService(private val accountRepository: AccountRepository) {


    @Transactional
    fun createAccount(request: AccountRequest): Account {
        val iban = request.iban
        if (accountRepository.isIbanExists(iban)) {
            throw ApplicationException.AccountAlreadyExists(iban)
        }

        val newRecord = accountRepository.newRecord()
        newRecord.iban = iban
        newRecord.type = request.type
        accountRepository.store(newRecord)
        return newRecord.toDto()
    }

}