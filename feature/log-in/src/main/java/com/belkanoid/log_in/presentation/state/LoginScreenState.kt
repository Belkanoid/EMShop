package com.belkanoid.log_in.presentation.state

sealed class LoginScreenState {

    data object Empty: LoginScreenState()

    data class NameField(val errorState: String?): LoginScreenState()
    data class SurnameField(val errorState: String?): LoginScreenState()
    data class PhoneField(val errorState: String?): LoginScreenState()

    data object Success: LoginScreenState()

}