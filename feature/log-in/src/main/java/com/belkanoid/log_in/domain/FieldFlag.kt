package com.belkanoid.log_in.domain

data class FieldFlag(
    var isNameFieldValid: Boolean = false,
    var isSurnameFieldValid: Boolean = false,
    val isPhoneFieldValid: Boolean = false,
) {
    fun ensureAllField() = isNameFieldValid and isSurnameFieldValid and isPhoneFieldValid
}