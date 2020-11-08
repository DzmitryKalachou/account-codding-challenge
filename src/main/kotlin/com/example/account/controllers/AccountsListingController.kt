package com.example.account.controllers

import com.example.account.models.dto.Account
import com.example.account.services.accounts.AccountListingService
import com.example.db.enums.AccountType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class AccountsListingController(private val accountListingService: AccountListingService) {

    @GetMapping("/accounts")
    fun getAccounts(@RequestParam("type") type: AccountType): ResponseEntity<List<Account>> {
        return ResponseEntity.ok(accountListingService.getAccounts(type))
    }

}