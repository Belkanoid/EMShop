package com.belkanoid.log_in.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belkanoid.core.util.TextValidate.isCyrillic
import com.belkanoid.log_in.domain.LoginRepository
import com.belkanoid.log_in.naviation.LoginDirections
import com.belkanoid.log_in.presentation.state.LoginFields
import com.belkanoid.navigation_api.NavigationApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val navigationApi: NavigationApi<LoginDirections>
) : ViewModel() {

    private val _nameField = MutableStateFlow("")
    private val _surnameField = MutableStateFlow("")
    private val _phoneField = MutableStateFlow("")
    val isAllFieldsValid = combine(_nameField, _surnameField, _phoneField) { name, surname, phone ->
        isNameValid(name) and isSurnameValid(surname) and isPhoneValid(phone)
    }

    private val _fieldsState = MutableStateFlow(LoginFields())
    val fieldState = _fieldsState.asStateFlow()


    fun updateNameField(name: String) {
        _nameField.value = name
    }

    fun updateSurnameField(surname: String) {
        _surnameField.value = surname
    }

    fun updatePhoneField(phone: String) {
        _phoneField.value = phone
    }


    private fun isNameValid(name: String) = name.isNotEmpty() && proceed(isCyrillic(name)) {
        _fieldsState.value.copy(nameErrorMessage = it)
    }

    private fun isSurnameValid(surname: String) = surname.isNotEmpty() && proceed(isCyrillic(surname)) {
            _fieldsState.value.copy(surnameErrorMessage = it)
        }

    private fun isPhoneValid(number: String) = proceed(number.length == 16) {
        _fieldsState.value.copy(phoneErrorMessage = it)
    }

    private fun proceed(condition: Boolean, actionBlock: (String?) -> LoginFields): Boolean {
        return if (condition) {
            _fieldsState.value = actionBlock(null)
            true
        } else {
            _fieldsState.value = actionBlock("error")
            false
        }
    }

    fun saveUser(name: String, surname: String, phone: String) = viewModelScope.launch {
        repository.saveUsername(name, surname, phone)
        navigationApi.navigate(LoginDirections.ToTabs)
    }


}