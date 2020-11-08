package com.example.account.services.accounts

import com.example.db.tables.records.TransactionRecord
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import kotlin.test.assertEquals

class AccountBalanceCalculatorComponentTest {

    companion object {
        private const val senderIban = "DE04500105174392161966"
        private const val receiverIban = "DE04500105174392161967"
    }

    private val component = AccountBalanceCalculatorComponent()


    @Test
    fun calculateBalancePositive() {
        val transactionIncomming = mock<TransactionRecord> {
            on { amount } doReturn 1
            on { senderIban } doReturn senderIban
            on { receiverIban } doReturn receiverIban
        }

        val transactionOutgoing = mock<TransactionRecord> {
            on { amount } doReturn 1
            on { senderIban } doReturn receiverIban
            on { receiverIban } doReturn senderIban
        }

        assertEquals(0, component.calculateBalance(receiverIban, listOf(transactionIncomming, transactionOutgoing)))
    }

}