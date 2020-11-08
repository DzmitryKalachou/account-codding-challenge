package com.example.account.services

import com.example.account.errors.ApplicationException
import com.example.account.models.dto.Transaction
import com.example.account.models.dto.extensions.toDto
import com.example.account.models.requests.DepositRequest
import com.example.account.models.requests.TransferRequest
import com.example.account.repositories.AccountRepository
import com.example.account.repositories.TransactionRepository
import com.example.account.services.accounts.AccountBalanceCalculatorComponent
import com.example.db.enums.AccountType
import com.example.db.tables.records.AccountRecord
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TransferService(private val accountRepository: AccountRepository,
                      private val accountBalanceCalculatorComponent: AccountBalanceCalculatorComponent,
                      private val transactionRepository: TransactionRepository) {


    @Transactional
    fun depositMoney(iban: String, request: DepositRequest): Transaction {
        val receiverAccountRecord = accountRepository.getByIban(iban)

        checkAccountLockStatus(receiverAccountRecord)

        val transactionRecord = transactionRepository.newRecord()
        transactionRecord.receiverIban = iban
        transactionRecord.amount = request.amount
        transactionRepository.store(transactionRecord)
        return transactionRecord.toDto()
    }

    @Transactional
    fun transferMoney(sendingIban: String, receivingIban: String, request: TransferRequest): Transaction {
        val sendingAccountRecord = accountRepository.getByIban(sendingIban)
        val receivingAccountRecord = accountRepository.getByIban(receivingIban)

        checkAccountLockStatus(sendingAccountRecord)
        checkAccountLockStatus(receivingAccountRecord)
        checkTransferOperationAvailabilityForAccountTypes(sendingAccountRecord, receivingAccountRecord)

        val transactions = transactionRepository.getAccountTransactionsForUpdate(sendingIban)
        if (accountBalanceCalculatorComponent.calculateBalance(sendingIban, transactions) < request.amount) {
            throw ApplicationException.NotEnoughMoney(sendingIban)
        }

        val transactionRecord = transactionRepository.newRecord()
        transactionRecord.receiverIban = receivingIban
        transactionRecord.senderIban = sendingIban
        transactionRecord.amount = request.amount
        transactionRepository.store(transactionRecord)

        return transactionRecord.toDto()
    }

    private fun checkAccountLockStatus(accountRecord: AccountRecord) {
        if (accountRecord.locked) {
            throw ApplicationException.AccountLocked(accountRecord.iban)
        }
    }

    private fun checkTransferOperationAvailabilityForAccountTypes(senderAccountRecord: AccountRecord, receiverAccountRecord: AccountRecord) {
        if (senderAccountRecord.type == AccountType.SAVING) {
            if (receiverAccountRecord.type != AccountType.CHECKING) {
                throw ApplicationException.WithdrawalOnNotCheckingAccountNotAvailable(senderAccountRecord.iban)
            }
        } else if (senderAccountRecord.type == AccountType.PRIVATE_LOAN) {
            throw ApplicationException.WithdrawalNotAvailable(senderAccountRecord.iban)
        }
    }


}