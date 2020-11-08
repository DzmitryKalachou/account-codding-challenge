package com.example.account.controllers

import com.example.account.models.dto.Account
import com.example.account.models.requests.AccountRequest
import com.example.account.services.accounts.AccountCreationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid


@RestController
class AccountsCreationController(private val accountCreationService: AccountCreationService) {

    @PostMapping("/accounts")
    fun createAccount(@Valid @RequestBody request: AccountRequest): ResponseEntity<Account> {
        return ResponseEntity.ok(accountCreationService.createAccount(request))
    }

}