package com.example.account.repositories

import org.jooq.DSLContext
import org.jooq.UpdatableRecord
import org.jooq.impl.TableImpl

abstract class BaseRepository<R : UpdatableRecord<R>, T : TableImpl<R>>(private val jooq: DSLContext) {

    protected fun jooq(): DSLContext {
        return jooq
    }

    open fun newRecord(): R {
        return jooq().newRecord(table())
    }

    open fun store(record: R) {
        record.store()
        record.refresh()
    }

    protected abstract fun table(): T

}