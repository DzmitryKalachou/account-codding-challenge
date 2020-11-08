package com.example.account.services.accounts

import com.example.account.errors.ApplicationException
import com.example.account.models.dto.Account
import com.example.account.models.dto.extensions.toDto
import com.example.account.repositories.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AccountLockingService(private val accountRepository: AccountRepository) {

    @Transactional
    fun lockAccount(iban: String): Account {
        return setAccountLockState(iban, true)
    }

    @Transactional
    fun unlockAccount(iban: String): Account {
        return setAccountLockState(iban, false)
    }

    private fun setAccountLockState(iban: String, lock: Boolean): Account {
        val accountRecord = accountRepository.getByIban(iban)
        if (accountRecord.locked == lock) {
            throw ApplicationException.AccountAlreadyHasRequestedLockState(iban)
        }
        accountRecord.locked = lock
        accountRepository.store(accountRecord)
        return accountRecord.toDto()
    }

}