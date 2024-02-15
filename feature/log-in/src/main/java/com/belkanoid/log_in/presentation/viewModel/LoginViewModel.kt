package com.belkanoid.log_in.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.belkanoid.log_in.domain.FieldFlag
import com.belkanoid.log_in.domain.LoginRepository
import com.belkanoid.log_in.naviation.LoginDirections
import com.belkanoid.log_in.presentation.state.LoginScreenState
import com.belkanoid.navigation_api.NavigationApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(
    private val repository: LoginRepository,
    private val navigationApi: NavigationApi<LoginDirections>
) : ViewModel() {

    private var fieldFlag = FieldFlag()
    private val _state = MutableStateFlow<LoginScreenState>(LoginScreenState.Empty)
    val state = _state.asStateFlow()

    fun validateName(name: String) {
        fieldFlag = if(name.isEmpty()) {
            _state.value = LoginScreenState.NameField(null)
            fieldFlag.copy(isNameFieldValid = false)
        }
        else if (validate(name)) {
            _state.value = LoginScreenState.NameField(null)
            fieldFlag.copy(isNameFieldValid = true)
        } else {
            _state.value = LoginScreenState.NameField("error")
            fieldFlag.copy(isNameFieldValid = false)
        }
        isAllFieldValid()
    }


    fun validateSurname(surname: String) {
        fieldFlag = if(surname.isEmpty()) {
            fieldFlag.copy(isSurnameFieldValid = false)
        }
        else if (validate(surname)) {
            _state.value = LoginScreenState.SurnameField(null)
            fieldFlag.copy(isSurnameFieldValid = true)
        } else {
            _state.value = LoginScreenState.SurnameField("error")
            fieldFlag.copy(isSurnameFieldValid = false)
        }
        isAllFieldValid()
    }

    fun validatePhone(number: String) {
        fieldFlag = if (number.length == 16) {
            fieldFlag.copy(isPhoneFieldValid = true)
        }else {
            fieldFlag.copy(isPhoneFieldValid = false)
        }
        isAllFieldValid()

    }

    fun saveUser(name: String, surname: String, phone: String) {
        viewModelScope.launch {
            repository.saveUsername(name, surname, phone)
        }
        navigationApi.navigate(LoginDirections.ToTabs)
    }

    private fun isAllFieldValid() {
        if (fieldFlag.ensureAllField()) {
            _state.value = LoginScreenState.Success
        }else{
            _state.value = LoginScreenState.Empty
        }
    }

    private fun validate(value: String): Boolean {
        value.forEach {
            if (Character.UnicodeBlock.of(it) != Character.UnicodeBlock.CYRILLIC) {
                return false
            }
        }
        return true
    }

}