package com.example.account.controllers

import com.example.account.models.responses.BalanceResponse
import com.example.account.services.accounts.AccountInformationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


@RestController
class AccountsInformationalController(private val accountInformationService: AccountInformationService) {

    @GetMapping("/accounts/{iban}/balance")
    fun getAccountBalance(@PathVariable("iban") iban: String): ResponseEntity<BalanceResponse> {
        return ResponseEntity.ok(accountInformationService.getIbanBalance(iban))
    }

}