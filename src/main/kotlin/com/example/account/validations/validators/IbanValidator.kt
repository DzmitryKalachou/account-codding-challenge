package com.example.account.validations.validators

import com.example.account.validations.annotations.ValidIban
import org.iban4j.IbanUtil
import org.springframework.stereotype.Component
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

@Component
class IbanValidator : ConstraintValidator<ValidIban, String> {

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return if (value != null) {
            try {
                IbanUtil.validate(value)
                true
            } catch (e: Exception) {
                false
            }
        } else {
            false
        }
    }
}

