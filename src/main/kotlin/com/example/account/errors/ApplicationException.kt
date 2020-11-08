package com.example.account.errors

import org.springframework.http.HttpStatus

enum class ErrorCode {
    INTERNAL_SERVER_ERROR,
    ACCOUNT_NOT_EXISTS,
    ACCOUNT_ALREADY_HAS_REQUESTED_LOCK_STATE,
    ACCOUNT_ALREADY_EXISTS,
    WITHDRAWAL_NOT_AVAILABLE,
    ACCOUNT_LOCKED,
    NOT_ENOUGH_MONEY
}

sealed class ApplicationException(override val message: String,
                                  val statusCode: HttpStatus = HttpStatus.BAD_REQUEST,
                                  val errorCode: ErrorCode = ErrorCode.INTERNAL_SERVER_ERROR) : RuntimeException(message) {


    class AccountNotExists(iban: String) : ApplicationException(
            "Account $iban not exists",
            HttpStatus.BAD_REQUEST,
            ErrorCode.ACCOUNT_NOT_EXISTS
    )

    class AccountAlreadyHasRequestedLockState(iban: String) : ApplicationException(
            "Account $iban already has requested lock state",
            HttpStatus.BAD_REQUEST,
            ErrorCode.ACCOUNT_ALREADY_HAS_REQUESTED_LOCK_STATE
    )

    class AccountAlreadyExists(iban: String) : ApplicationException(
            "Account already exists with $iban",
            HttpStatus.BAD_REQUEST,
            ErrorCode.ACCOUNT_ALREADY_EXISTS
    )

    class WithdrawalNotAvailable(iban: String) : ApplicationException(
            "Withdrawal from account $iban is not available",
            HttpStatus.FORBIDDEN,
            ErrorCode.WITHDRAWAL_NOT_AVAILABLE
    )

    class WithdrawalOnNotCheckingAccountNotAvailable(iban: String) : ApplicationException(
            "Withdrawal from saving account $iban to not checking account is not available",
            HttpStatus.FORBIDDEN,
            ErrorCode.WITHDRAWAL_NOT_AVAILABLE
    )

    class AccountLocked(iban: String) : ApplicationException(
            "Account $iban is locked",
            HttpStatus.FORBIDDEN,
            ErrorCode.ACCOUNT_LOCKED
    )

    class NotEnoughMoney(iban: String) : ApplicationException(
            "Account $iban doesn't have enough money on balance",
            HttpStatus.FORBIDDEN,
            ErrorCode.NOT_ENOUGH_MONEY
    )


}
