package com.example.account.controllers

import com.example.account.models.dto.Transaction
import com.example.account.services.accounts.AccountInformationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class AccountsTransactionsController(private val accountInformationService: AccountInformationService) {

    @GetMapping("/accounts/{iban}/transactions")
    fun getAccountTransactions(@PathVariable("iban") iban: String): ResponseEntity<List<Transaction>> {
        return ResponseEntity.ok(accountInformationService.getIbanTransactions(iban))
    }

}