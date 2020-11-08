package com.example.account.controllers

import com.example.account.models.dto.Account
import com.example.account.services.accounts.AccountLockingService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/accounts")
class AccountLockingController(private val accountLockingService: AccountLockingService) {

    @PutMapping("/{iban}/lock")
    fun lockAccount(@PathVariable("iban") iban: String): ResponseEntity<Account> {
        return ResponseEntity.ok(accountLockingService.lockAccount(iban))
    }

    @DeleteMapping("/{iban}/lock")
    fun unlockAccount(@PathVariable("iban") iban: String): ResponseEntity<Account> {
        return ResponseEntity.ok(accountLockingService.unlockAccount(iban))
    }

}