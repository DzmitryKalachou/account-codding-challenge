package com.example.account.repositories

import com.example.account.errors.ApplicationException
import com.example.db.Tables.ACCOUNT
import com.example.db.enums.AccountType
import com.example.db.tables.Account
import com.example.db.tables.records.AccountRecord
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

@Repository
class AccountRepository(jooq: DSLContext) : BaseRepository<AccountRecord, Account>(jooq) {

    fun getByType(type: AccountType): List<AccountRecord> {
        return jooq().selectFrom(ACCOUNT).where(ACCOUNT.TYPE.eq(type)).fetch()
    }

    fun getByIbanOpt(iban: String): AccountRecord? {
        return jooq().selectFrom(ACCOUNT).where(ACCOUNT.IBAN.equalIgnoreCase(iban)).fetchOptional().orElse(null)
    }

    fun getByIban(iban: String): AccountRecord {
        return getByIbanOpt(iban) ?: throw ApplicationException.AccountNotExists(iban)
    }

    fun isIbanExists(iban: String): Boolean {
        return jooq().fetchExists(ACCOUNT, ACCOUNT.IBAN.equalIgnoreCase(iban))
    }

    override fun table(): Account = ACCOUNT

}