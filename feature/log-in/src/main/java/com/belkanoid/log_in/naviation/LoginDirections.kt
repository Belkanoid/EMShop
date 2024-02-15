package com.belkanoid.log_in.naviation

sealed interface LoginDirections {

    data object ToTabs : LoginDirections

}