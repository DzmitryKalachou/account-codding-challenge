package com.example.account.controllers

import com.example.account.models.dto.Transaction
import com.example.account.models.requests.DepositRequest
import com.example.account.models.requests.TransferRequest
import com.example.account.services.TransferService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/accounts")
class AccountMoneyOperationsController(private val transferService: TransferService) {

    @PostMapping("/{iban}/deposit")
    fun depositMoney(@PathVariable("iban") iban: String,
                     @Valid @RequestBody request: DepositRequest): ResponseEntity<Transaction> {
        return ResponseEntity.ok(transferService.depositMoney(iban, request))
    }

    @PostMapping("/{sendingIban}/transfer/{receivingIban}")
    fun transferMoney(@PathVariable("sendingIban") sendingIban: String,
                      @PathVariable("receivingIban") receivingIban: String,
                      @Valid @RequestBody request: TransferRequest): ResponseEntity<Transaction> {
        return ResponseEntity.ok(transferService.transferMoney(sendingIban, receivingIban, request))
    }

}