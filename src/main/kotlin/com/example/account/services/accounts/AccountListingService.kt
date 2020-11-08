package com.example.account.services.accounts

import com.example.account.models.dto.Account
import com.example.account.models.dto.extensions.toDto
import com.example.account.repositories.AccountRepository
import com.example.db.enums.AccountType
import org.springframework.stereotype.Service

@Service
class AccountListingService(private val accountRepository: AccountRepository) {

    fun getAccounts(type: AccountType): List<Account> {
        return accountRepository.getByType(type).map { it.toDto() }
    }
}