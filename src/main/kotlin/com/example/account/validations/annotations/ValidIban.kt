package com.example.account.validations.annotations

import com.example.account.validations.validators.IbanValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@Constraint(validatedBy = [IbanValidator::class])
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValidIban(val message: String = "invalid iban", val groups: Array<KClass<*>> = [], val payload: Array<KClass<out Payload>> = [])
