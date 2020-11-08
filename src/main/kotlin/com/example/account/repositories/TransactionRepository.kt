package com.example.account.repositories

import com.example.db.Tables.TRANSACTION
import com.example.db.tables.Transaction
import com.example.db.tables.records.TransactionRecord
import org.jooq.DSLContext
import org.jooq.SelectSeekStep1
import org.springframework.stereotype.Repository
import java.sql.Timestamp

@Repository
class TransactionRepository(jooq: DSLContext) : BaseRepository<TransactionRecord, Transaction>(jooq) {

    fun getAccountTransactions(iban: String): List<TransactionRecord> {
        return getAccountTransactionsQuery(iban).fetch()
    }

    fun getAccountTransactionsForUpdate(iban: String): List<TransactionRecord> {
        return getAccountTransactionsQuery(iban).forUpdate().fetch()
    }

    private fun getAccountTransactionsQuery(iban: String): SelectSeekStep1<TransactionRecord, Timestamp> {
        return jooq()
                .selectFrom(TRANSACTION)
                .where(TRANSACTION.SENDER_IBAN.equalIgnoreCase(iban).or(TRANSACTION.RECEIVER_IBAN.equalIgnoreCase(iban)))
                .orderBy(TRANSACTION.CREATE_TIMESTAMP.desc())
    }

    override fun table(): Transaction = TRANSACTION

}