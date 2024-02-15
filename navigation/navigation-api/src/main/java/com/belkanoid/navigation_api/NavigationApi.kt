package com.belkanoid.navigation_api

interface NavigationApi<DIRECTION> {

    fun navigate(direction: DIRECTION)

}